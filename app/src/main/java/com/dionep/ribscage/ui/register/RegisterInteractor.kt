package com.dionep.ribscage.ui.register

import com.dionep.mvi.Feature
import com.dionep.ribscage.base.MviInteractor
import com.dionep.ribscage.ui.register.RegisterFeature.*
import com.dionep.ribscage.ui.register.RegisterInteractor.*
import com.dionep.ribscage.ui.root.RootRouter
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

@RibInteractor
class RegisterInteractor : MviInteractor<RegisterPresenter, RegisterRouter, State, News>() {

  @Inject
  lateinit var presenter: RegisterPresenter

  @Inject
  override lateinit var feature: RegisterFeature

  @Inject
  lateinit var rootRouter: RootRouter

  override fun renderState(state: State) {
  }

  override fun handleNews(news: News) {
    when (news) {
//      is News.Failure -> TODO()
      is News.RegisterSuccess -> rootRouter.attachProfile()
    }
  }

  interface RegisterPresenter {


    sealed class UiEvents {
    }
  }

}
