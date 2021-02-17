package com.dionep.ribscage.ui.profile

import android.content.Context
import android.view.LayoutInflater
import androidx.core.view.isVisible
import com.dionep.ribscage.base.MviView
import com.dionep.ribscage.databinding.RibProfileBinding
import com.dionep.ribscage.ui.profile.ProfileFeature.*
import com.dionep.ribscage.ui.profile.ProfileInteractor.*
import com.dionep.ribscage.ui.profile.ProfileInteractor.ProfilePresenter.*

/**
 * Top level view for {@link ProfileBuilder.ProfileScope}.
 */
class ProfileView(
    context: Context
) : MviView<UiEvents, State>(context), ProfilePresenter {

    private val viewBinding = RibProfileBinding.inflate(LayoutInflater.from(context), this, true)

    override fun renderState(state: State) {
        with(viewBinding) {
            loading.isVisible = state.isLoading
            tvName.text = state.profile?.name
            tvExit.setOnClickListener { acceptEvent(UiEvents.Exit) }
        }
    }

}
