package com.dionep.ribscage.ui.register

import com.dionep.ribscage.base.BasePresenter
import com.dionep.ribscage.base.MviInteractor
import com.dionep.ribscage.ui.register.RegisterFeature.News
import com.dionep.ribscage.ui.register.RegisterFeature.State
import com.dionep.ribscage.ui.register.RegisterInteractor.RegisterPresenter
import com.dionep.ribscage.ui.register.RegisterInteractor.RegisterPresenter.*
import com.dionep.ribscage.ui.root.RootRouter
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

@RibInteractor
class RegisterInteractor : MviInteractor<RegisterPresenter, RegisterRouter, State, News, UiEvents>() {

  @Inject
  override lateinit var presenter: RegisterPresenter

  @Inject
  override lateinit var feature: RegisterFeature

  @Inject
  lateinit var rootRouter: RootRouter

  override fun handleUiEvent(uiEvent: UiEvents) {
    when (uiEvent) {
      is UiEvents.Back -> rootRouter.attachLogin()
      is UiEvents.Register -> feature.accept(RegisterFeature.Msg.Register(uiEvent.name, uiEvent.password))
    }
  }

  override fun handleNews(news: News) {
    when (news) {
      is News.Failure -> presenter.showMessage(news.message)
      is News.RegisterSuccess -> rootRouter.attachProfile()
    }
  }

  interface RegisterPresenter: BasePresenter<UiEvents, State> {

    sealed class UiEvents {
      object Back : UiEvents()
      data class Register(val name: String, val password: String) : UiEvents()
    }

  }

}
