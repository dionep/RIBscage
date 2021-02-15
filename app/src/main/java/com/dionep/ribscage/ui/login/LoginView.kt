package com.dionep.ribscage.ui.login

import android.content.Context
import android.view.LayoutInflater
import com.dionep.ribscage.base.MviView
import com.dionep.ribscage.databinding.RibLoginBinding
import com.dionep.ribscage.ui.login.LoginInteractor.LoginPresenter
import com.dionep.ribscage.ui.login.LoginInteractor.LoginPresenter.UiEvents

class LoginView(
    context: Context
) : MviView<UiEvents>(context), LoginPresenter {

    private val viewBinding = RibLoginBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        with(viewBinding) {
            btnLogin.setOnClickListener {
                acceptEvent(UiEvents.LogIn(etName.text.toString(), etPassword.text.toString()))
            }
            btnRegister.setOnClickListener {
                acceptEvent(UiEvents.ToRegister)
            }
        }
    }

    override fun renderState(state: LoginFeature.State) {

    }

}