package com.dionep.ribscage.ui.profile

import com.dionep.mvi.Feature
import com.dionep.mvi.SideEffect
import com.dionep.mvi.Update
import com.dionep.ribscage.data.ApiClient
import com.dionep.ribscage.entity.User
import com.dionep.ribscage.ui.profile.ProfileFeature.*
import com.dionep.ribscage.utils.awaitFolding
import kotlinx.coroutines.flow.flowOf

class ProfileFeature(
    apiClient: ApiClient
) : Feature<State, Cmd, Msg, News>(
    initialState = State(),
    initialMessages = setOf(Msg.LoadProfile),
    reducer = { msg, state ->
        when (msg) {
            is Msg.LoadProfile -> Update(
                state = state.copy(isLoading = true, isErrorVisible = false),
                cmd = Cmd.LoadProfile
            )
            is Msg.SetProfile -> Update(
                state = state.copy(
                    isLoading = false,
                    profile = msg.user
                )
            )
            is Msg.SetErrorState -> Update(
                state = state.copy(
                    isLoading = false,
                    isErrorVisible = true
                )
            )
        }
    },
    commandHandler = { cmd ->
        when (cmd) {
            Cmd.LoadProfile -> flowOf(
                apiClient.getProfileAsync()
                    .awaitFolding(
                        { SideEffect(Msg.SetProfile(it)) },
                        { SideEffect(Msg.SetErrorState, News.Failure(it.message ?: "Error")) }
                    )
            )
        }
    }
) {

    data class State(
        val isLoading: Boolean = true,
        val isErrorVisible: Boolean = false,
        val profile: User? = null
    )

    sealed class Cmd {
        object LoadProfile : Cmd()
    }

    sealed class Msg {
        object LoadProfile : Msg()
        data class SetProfile(val user: User) : Msg()
        object SetErrorState : Msg()
    }

    sealed class News {
        data class Failure(val message: String) : News()
    }

}