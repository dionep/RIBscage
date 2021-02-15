package com.dionep.ribscage.ui.profile

import com.dionep.ribscage.base.BasePresenter
import com.dionep.ribscage.base.MviInteractor
import com.dionep.ribscage.ui.profile.ProfileFeature.*
import com.dionep.ribscage.ui.profile.ProfileInteractor.*
import com.dionep.ribscage.ui.profile.ProfileInteractor.ProfilePresenter.*
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

@RibInteractor
class ProfileInteractor : MviInteractor<ProfilePresenter, ProfileRouter, State, News, UiEvents>() {

  @Inject
  override lateinit var presenter: ProfilePresenter

  @Inject
  override lateinit var feature: ProfileFeature

  override fun handleUiEvent(uiEvent: UiEvents) {

  }

  override fun renderState(state: State) {

  }

  override fun handleNews(news: News) {

  }

  interface ProfilePresenter: BasePresenter<UiEvents> {

    sealed class UiEvents()
  }

}
