package com.dionep.ribscage.ui.login

import com.dionep.ribscage.base.MviInteractor
import com.dionep.ribscage.ui.login.LoginFeature.*
import com.dionep.ribscage.ui.login.LoginInteractor.*
import com.dionep.ribscage.ui.root.RootRouter
import com.uber.rib.core.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@RibInteractor
class LoginInteractor : MviInteractor<LoginPresenter, LoginRouter, State, News>() {

  @Inject
  lateinit var presenter: LoginPresenter

  @Inject
  override lateinit var feature: LoginFeature

  @Inject
  lateinit var rootRouter: RootRouter

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
    coroutineScope.launch {
      presenter.startEvent().collect(::handleUiEvent)
    }
  }

  private fun handleUiEvent(uiEvents: LoginPresenter.UiEvents) {
    when(uiEvents) {
      is LoginPresenter.UiEvents.LogIn -> feature.accept(Msg.LogIn(uiEvents.name, uiEvents.password))
      is LoginPresenter.UiEvents.ToRegister -> rootRouter.attachRegister()
    }
  }

  override fun renderState(state: State) {
    println(state)
  }

  override fun handleNews(news: News) {
    when (news) {
      is News.LoginSuccess -> rootRouter.attachProfile()
      is News.Failure -> println(news)
    }
  }

  interface LoginPresenter {

    sealed class UiEvents {
      data class LogIn(val name: String, val password: String) : UiEvents()
      object ToRegister : UiEvents()
    }

    fun startEvent(): SharedFlow<UiEvents>

  }

}
