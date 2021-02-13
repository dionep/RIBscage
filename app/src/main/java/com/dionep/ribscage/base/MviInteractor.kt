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
abstract class MviInteractor<P, R: ViewRouter<*, *, *>, State, News>: Interactor<P, R>() {

  private val job = SupervisorJob()
  val coroutineScope: CoroutineScope
    get() = CoroutineScope(Dispatchers.Main + job)

  abstract val feature: Feature<State, *, *, News>

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

  abstract fun renderState(state: State)
  abstract fun handleNews(news: News)

  override fun willResignActive() {
    super.willResignActive()
    feature.dispose()
    job.cancel()
  }

}
