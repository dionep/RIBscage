package com.dionep.ribscage.ui.login

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.dionep.ribscage.databinding.RibLoggedOutBinding

/**
 * Top level view for {@link LoggedOutBuilder.LoggedOutScope}.
 */
class LoginView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private val viewBinding = RibLoggedOutBinding.inflate(LayoutInflater.from(context), this, true)

    fun setOnLoginClicked(l: (String, String) -> Unit) {
        with (viewBinding) {
            loginButton.setOnClickListener {
                l.invoke(etName.text.toString(), etPassword.text.toString())
            }
        }
    }

}