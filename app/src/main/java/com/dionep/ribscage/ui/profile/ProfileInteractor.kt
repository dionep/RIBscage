package com.dionep.ribscage.ui.profile

import com.dionep.ribscage.base.MviInteractor
import com.dionep.ribscage.ui.profile.ProfileFeature.*
import com.dionep.ribscage.ui.profile.ProfileInteractor.*
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

@RibInteractor
class ProfileInteractor : MviInteractor<ProfilePresenter, ProfileRouter, State, News>() {

  @Inject
  lateinit var presenter: ProfilePresenter

  @Inject
  override lateinit var feature: ProfileFeature

  interface ProfilePresenter {

  }

  override fun renderState(state: State) {

  }

  override fun handleNews(news: News) {

  }

}
