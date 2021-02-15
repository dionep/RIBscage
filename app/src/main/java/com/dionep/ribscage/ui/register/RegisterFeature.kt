package com.dionep.ribscage.ui.register

import com.dionep.mvi.Feature
import com.dionep.mvi.SideEffect
import com.dionep.mvi.Update
import com.dionep.ribscage.data.ApiClient
import com.dionep.ribscage.ui.register.RegisterFeature.*
import com.dionep.ribscage.utils.awaitFolding
import com.dionep.ribscage.utils.jsonRequestBodyOf
import kotlinx.coroutines.flow.flowOf

class RegisterFeature(
    apiClient: ApiClient
) : Feature<State, Cmd, Msg, News>(
    initialState = State(),
    reducer = { msg, state ->
        when (msg) {
            is Msg.Register -> Update(
                state = state.copy(isLoading = true),
                cmd = Cmd.Register(
                    name = msg.name,
                    password = msg.password
                )
            )
            is Msg.StopLoading -> Update(state.copy(isLoading = false))
        }
    },
     commandHandler = { cmd ->
         when (cmd) {
             is Cmd.Register -> flowOf(
                 apiClient.registerAsync(
                     jsonRequestBodyOf(
                         "name" to cmd.name,
                         "password" to cmd.password
                     )
                 ).awaitFolding(
                     { SideEffect(Msg.StopLoading, News.RegisterSuccess) },
                     { SideEffect(Msg.StopLoading, News.Failure(it.message ?: "Error occured")) }
                 )
             )
         }
     }
) {

    data class State(
        val isLoading: Boolean = false
    )

    sealed class Cmd {
        data class Register(val name: String, val password: String) : Cmd()
    }

    sealed class Msg {
        data class Register(val name: String, val password: String) : Msg()
        object StopLoading : Msg()
    }

    sealed class News {
        data class Failure(val message: String) : News()
        object RegisterSuccess : News()
    }

}