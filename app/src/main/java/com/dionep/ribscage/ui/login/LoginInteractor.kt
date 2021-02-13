package com.dionep.ribscage.ui.login

import com.dionep.ribscage.base.MviInteractor
import com.dionep.ribscage.ui.login.LoginFeature.*
import com.dionep.ribscage.ui.login.LoginInteractor.*
import com.uber.rib.core.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

/**
 * Coordinates Business Logic for [LoggedOutScope].
 *
 * TODO describe the logic of this scope.
 */

@RibInteractor
class LoginInteractor : MviInteractor<LoginPresenter, LoginRouter, State, News>() {

  @Inject
  lateinit var presenter: LoginPresenter

  @Inject
  override lateinit var feature: LoginFeature

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
    coroutineScope.launch {
      presenter.startEvent().collect(::handleUiEvent)
    }
  }

  private fun handleUiEvent(uiEvents: LoginPresenter.UiEvents) {
    when(uiEvents) {
      is LoginPresenter.UiEvents.LogIn -> feature.accept(Msg.LogIn(uiEvents.name, uiEvents.password))
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
  interface LoginPresenter {

    sealed class UiEvents {
      data class LogIn(val name: String, val password: String) : UiEvents()
    }

    fun startEvent(): SharedFlow<UiEvents>

  }

}
