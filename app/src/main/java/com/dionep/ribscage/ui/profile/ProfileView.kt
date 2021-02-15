package com.dionep.ribscage.ui.profile

import android.content.Context
import android.view.LayoutInflater
import com.dionep.ribscage.base.MviView
import com.dionep.ribscage.databinding.RibProfileBinding
import com.dionep.ribscage.ui.profile.ProfileInteractor.*
import com.dionep.ribscage.ui.profile.ProfileInteractor.ProfilePresenter.*

/**
 * Top level view for {@link ProfileBuilder.ProfileScope}.
 */
class ProfileView(
    context: Context
) : MviView<UiEvents>(context), ProfilePresenter {

    private val viewBinding = RibProfileBinding.inflate(LayoutInflater.from(context), this, true)

    init {

    }

    override fun renderState(state: ProfileFeature.State) {
        with(viewBinding) {
            tvName.text = state.profile?.name
        }
    }

}
