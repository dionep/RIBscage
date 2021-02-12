package com.dionep.ribscage.ui.login

import com.dionep.ribscage.base.MviInteractor
import com.dionep.ribscage.ui.login.LoginFeature.*
import com.dionep.ribscage.ui.login.LoginInteractor.*
import com.uber.rib.core.*
import kotlinx.coroutines.*
import javax.inject.Inject

/**
 * Coordinates Business Logic for [LoggedOutScope].
 *
 * TODO describe the logic of this scope.
 */

@RibInteractor
class LoginInteractor : MviInteractor<LoggedOutPresenter, LoginRouter, State, News>() {

  @Inject
  lateinit var presenter: LoggedOutPresenter

  @Inject
  override lateinit var feature: LoginFeature

  fun acceptUiEvent(uiEvents: LoggedOutPresenter.UiEvents) {
    when(uiEvents) {
      is LoggedOutPresenter.UiEvents.LogIn -> feature.accept(Msg.LogIn(uiEvents.name, uiEvents.password))
    }
  }

  override fun renderState(state: State) {
    println(state)
  }

  override fun handleNews(news: News) {
    println(news)
  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface LoggedOutPresenter {

    sealed class UiEvents {
      data class LogIn(val name: String, val password: String) : UiEvents()
    }

  }

}
