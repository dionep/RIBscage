package com.dionep.ribscage.ui.login

import com.dionep.mvi.Feature
import com.dionep.mvi.SideEffect
import com.dionep.mvi.Update
import com.dionep.ribscage.data.ApiClient
import com.dionep.ribscage.data.Prefs
import com.dionep.ribscage.ui.login.LoginFeature.*
import com.dionep.ribscage.utils.awaitFolding
import com.dionep.ribscage.utils.jsonRequestBodyOf
import kotlinx.coroutines.flow.flowOf

class LoginFeature(
    apiClient: ApiClient,
    prefs: Prefs
) : Feature<State, Cmd, Msg, News>(
    initialState = State(),
    reducer = { msg, state ->
        when (msg) {
            is Msg.LogIn ->
                Update(
                    state = state.copy(isLoading = true),
                    cmd = Cmd.LogIn(
                        name = msg.name,
                        password = msg.password
                    )
                )
            is Msg.StopLoading -> Update(state.copy(isLoading = false))
        }
    },
    commandHandler = { cmd ->
        when (cmd) {
            is Cmd.LogIn ->
                    apiClient.loginAsync(
                        jsonRequestBodyOf(
                            "name" to cmd.name,
                            "password" to cmd.password
                        )
                    ).awaitFolding(
                        {
                            prefs.authToken = it
                            SideEffect(Msg.StopLoading, News.LoginSuccess)
                        },
                        { SideEffect(Msg.StopLoading, News.Failure(it.message ?: "Error occured")) }
                    )
        }
    }
) {

    data class State(
        val isLoading: Boolean = false
    )

    sealed class Cmd {
        data class LogIn(val name: String, val password: String) : Cmd()
    }

    sealed class Msg {
        data class LogIn(val name: String, val password: String) : Msg()
        object StopLoading: Msg()
    }

    sealed class News {
        object LoginSuccess : News()
        data class Failure(val message: String) : News()
    }

}