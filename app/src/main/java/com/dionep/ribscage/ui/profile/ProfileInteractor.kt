package com.dionep.ribscage.ui.profile

import com.dionep.ribscage.base.BasePresenter
import com.dionep.ribscage.base.MviInteractor
import com.dionep.ribscage.data.Prefs
import com.dionep.ribscage.ui.profile.ProfileFeature.*
import com.dionep.ribscage.ui.profile.ProfileInteractor.*
import com.dionep.ribscage.ui.profile.ProfileInteractor.ProfilePresenter.*
import com.dionep.ribscage.ui.root.RootRouter
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

  @Inject
  lateinit var rootRouter: RootRouter

  @Inject
  lateinit var prefs: Prefs

  override fun handleUiEvent(uiEvent: UiEvents) {
    when (uiEvent) {
      is UiEvents.Exit -> {
        prefs.authToken = null
        rootRouter.attachLogin()
      }
    }
  }

  override fun handleNews(news: News) {
    when(news) {
      is News.Failure -> presenter.showMessage(news.message)
    }
  }

  interface ProfilePresenter: BasePresenter<UiEvents, State> {

    sealed class UiEvents {
      object Exit : UiEvents()
    }

  }

}
