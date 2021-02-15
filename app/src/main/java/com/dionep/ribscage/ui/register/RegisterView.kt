package com.dionep.ribscage.ui.register

import android.content.Context
import android.util.AttributeSet
import android.view.View

/**
 * Top level view for {@link RegisterBuilder.RegisterScope}.
 */
class RegisterView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : View(context, attrs, defStyle), RegisterInteractor.RegisterPresenter
