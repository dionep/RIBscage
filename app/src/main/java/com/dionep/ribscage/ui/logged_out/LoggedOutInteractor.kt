package com.dionep.ribscage.ui.logged_out

import com.dionep.ribscage.base.MviInteractor
import com.dionep.ribscage.ui.logged_out.LoggedOutFeature.*
import com.dionep.ribscage.ui.logged_out.LoggedOutInteractor.*
import com.uber.rib.core.*
import kotlinx.coroutines.*
import javax.inject.Inject

/**
 * Coordinates Business Logic for [LoggedOutScope].
 *
 * TODO describe the logic of this scope.
 */

@RibInteractor
class LoggedOutInteractor : MviInteractor<LoggedOutPresenter, LoggedOutRouter, State, News>() {

  @Inject
  lateinit var presenter: LoggedOutPresenter

  @Inject
  override lateinit var feature: LoggedOutFeature

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
