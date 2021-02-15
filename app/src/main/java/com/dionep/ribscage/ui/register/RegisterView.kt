package com.dionep.ribscage.ui.register

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.dionep.ribscage.base.MviView
import com.dionep.ribscage.databinding.RibRegisterBinding
import com.dionep.ribscage.ui.register.RegisterInteractor.*
import com.dionep.ribscage.ui.register.RegisterInteractor.RegisterPresenter.*

/**
 * Top level view for {@link RegisterBuilder.RegisterScope}.
 */
class RegisterView @JvmOverloads constructor(
    context: Context
) : MviView<UiEvents>(context), RegisterPresenter {

    private val viewBinding = RibRegisterBinding.inflate(LayoutInflater.from(context), this, true)

    init {

    }

}
