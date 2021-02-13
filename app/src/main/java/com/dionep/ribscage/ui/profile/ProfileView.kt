package com.dionep.ribscage.ui.profile

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.dionep.ribscage.R
import com.dionep.ribscage.databinding.RibProfileBinding
import com.dionep.ribscage.ui.profile.ProfileInteractor.*

/**
 * Top level view for {@link ProfileBuilder.ProfileScope}.
 */
class ProfileView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), ProfilePresenter {

    private val viewBinding = RibProfileBinding.inflate(LayoutInflater.from(context), this, true)

    init {

    }

}
