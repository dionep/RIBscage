package com.dionep.ribscage.ui.login

import com.dionep.ribscage.base.BasePresenter
import com.dionep.ribscage.base.MviInteractor
import com.dionep.ribscage.ui.login.LoginFeature.*
import com.dionep.ribscage.ui.login.LoginInteractor.*
import com.dionep.ribscage.ui.login.LoginInteractor.LoginPresenter.*
import com.dionep.ribscage.ui.root.RootRouter
import com.uber.rib.core.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@RibInteractor
class LoginInteractor : MviInteractor<LoginPresenter, LoginRouter, State, News, UiEvents>() {

  @Inject
  override lateinit var presenter: LoginPresenter

  @Inject
  override lateinit var feature: LoginFeature

  @Inject
  lateinit var rootRouter: RootRouter

  override fun handleUiEvent(uiEvent: UiEvents) {
    when(uiEvent) {
      is UiEvents.LogIn -> feature.accept(Msg.LogIn(uiEvent.name, uiEvent.password))
      is UiEvents.ToRegister -> rootRouter.attachRegister()
    }
  }

  override fun handleNews(news: News) {
    when (news) {
      is News.LoginSuccess -> rootRouter.attachProfile()
      is News.Failure -> presenter.showMessage(news.message)
    }
  }

  interface LoginPresenter: BasePresenter<UiEvents, State> {

    sealed class UiEvents {
      data class LogIn(val name: String, val password: String) : UiEvents()
      object ToRegister : UiEvents()
    }

  }

}