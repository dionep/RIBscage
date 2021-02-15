package com.dionep.ribscage.ui.register

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.dionep.ribscage.base.MviView
import com.dionep.ribscage.databinding.RibRegisterBinding
import com.dionep.ribscage.ui.register.RegisterInteractor.*
import com.dionep.ribscage.ui.register.RegisterInteractor.RegisterPresenter.*
import kotlinx.coroutines.flow.SharedFlow

/**
 * Top level view for {@link RegisterBuilder.RegisterScope}.
 */
class RegisterView(
    context: Context
) : MviView<UiEvents>(context), RegisterPresenter {

    private val viewBinding = RibRegisterBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        with(viewBinding) {
            toolbar.setNavigationOnClickListener {
                acceptEvent(UiEvents.Back)
            }
            btnRegister.setOnClickListener {
                acceptEvent(UiEvents.Register(etName.text.toString(), etPassword.text.toString()))
            }
        }
    }

    override fun renderState(state: RegisterFeature.State) {

    }

}