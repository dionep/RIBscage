package com.dionep.ribscage.base

import com.dionep.mvi.Feature
import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.ViewRouter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Suppress("FINITE_BOUNDS_VIOLATION_IN_JAVA")
abstract class MviInteractor<P, R: ViewRouter<*, *, *>, State, News, UiEvents>: Interactor<P, R>() {

  private val job = SupervisorJob()
  private val coroutineScope: CoroutineScope
    get() = CoroutineScope(Dispatchers.Main + job)

  abstract val feature: Feature<State, *, *, News>
  abstract val presenter: BasePresenter<UiEvents, State>

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
    coroutineScope.launch {
      feature.stateFlow.collect(presenter::renderState)
    }
    coroutineScope.launch {
      feature.newsReceiveChannel.collect(::handleNews)
    }
    coroutineScope.launch {
      presenter.uiEvents().collect(::handleUiEvent)
    }
  }

  abstract fun handleNews(news: News)
  abstract fun handleUiEvent(uiEvent: UiEvents)

  override fun willResignActive() {
    super.willResignActive()
    feature.dispose()
    job.cancel()
  }

}
