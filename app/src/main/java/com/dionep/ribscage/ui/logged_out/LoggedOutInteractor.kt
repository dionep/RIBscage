package com.dionep.ribscage.ui.logged_out

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * Coordinates Business Logic for [LoggedOutScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class LoggedOutInteractor : Interactor<LoggedOutInteractor.LoggedOutPresenter, LoggedOutRouter>() {

  @Inject
  lateinit var presenter: LoggedOutPresenter

  @Inject
  lateinit var feature: LoggedOutFeature

  val job = SupervisorJob()
  val coroutineScope: CoroutineScope
    get() = CoroutineScope(Dispatchers.Main + job)

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
    coroutineScope.launch {
      feature.stateFlow
        .collect { renderState(it) }
    }
    coroutineScope.launch {
      for (news in feature.newsReceiveChannel) {
        handleNews(news)
      }
    }
  }

  override fun willResignActive() {
    super.willResignActive()
    feature.dispose()
    job.cancel()
  }

  private fun renderState(state: LoggedOutFeature.State) {
    println(state)
  }

  private fun handleNews(news: LoggedOutFeature.News) {
    println(news)
  }

  fun acceptUiEvent(uiEvents: LoggedOutPresenter.UiEvents) {
    when(uiEvents) {
      is LoggedOutPresenter.UiEvents.LogIn -> feature.accept(LoggedOutFeature.Msg.LogIn(uiEvents.name, uiEvents.password))
    }
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
