package com.dionep.ribscage.ui.login

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.dionep.ribscage.databinding.RibLoggedOutBinding
import com.dionep.ribscage.ui.login.LoginInteractor.*
import com.dionep.ribscage.ui.login.LoginInteractor.LoginPresenter.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlin.coroutines.coroutineContext

/**
 * Top level view for {@link LoggedOutBuilder.LoggedOutScope}.
 */
class LoginView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), LoginPresenter {

    private val viewBinding = RibLoggedOutBinding.inflate(LayoutInflater.from(context), this, true)

    private val eventsFlow = MutableSharedFlow<UiEvents>(extraBufferCapacity = 64)

    init {
        with(viewBinding) {
            loginButton.setOnClickListener {
                eventsFlow.tryEmit(UiEvents.LogIn(etName.text.toString(), etPassword.text.toString()))
            }
        }
    }

    override fun startEvent(): SharedFlow<UiEvents> = eventsFlow.asSharedFlow()

}