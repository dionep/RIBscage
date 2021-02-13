package com.dionep.ribscage.ui.login

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.dionep.ribscage.base.MviView
import com.dionep.ribscage.databinding.RibLoggedOutBinding
import com.dionep.ribscage.ui.login.LoginInteractor.*
import com.dionep.ribscage.ui.login.LoginInteractor.LoginPresenter.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlin.coroutines.coroutineContext

class LoginView @JvmOverloads constructor(
    context: Context
) : MviView<UiEvents>(context), LoginPresenter {

    private val viewBinding = RibLoggedOutBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        with(viewBinding) {
            loginButton.setOnClickListener {
                acceptEvent(UiEvents.LogIn(etName.text.toString(), etPassword.text.toString()))
            }
        }
    }

    override fun startEvent(): SharedFlow<UiEvents> = eventsFlow.asSharedFlow()

}