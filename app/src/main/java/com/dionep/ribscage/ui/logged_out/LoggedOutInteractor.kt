package com.dionep.ribscage.ui.logged_out

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

/**
 * Coordinates Business Logic for [LoggedOutScope].
 *
 * TODO describe the logic of this scope.
 */
@RibInteractor
class LoggedOutInteractor : Interactor<LoggedOutInteractor.LoggedOutPresenter, LoggedOutRouter>() {

  @Inject
  lateinit var presenter: LoggedOutPresenter

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
  }

  override fun willResignActive() {
    super.willResignActive()

  }

  /**
   * Presenter interface implemented by this RIB's view.
   */
  interface LoggedOutPresenter {

    sealed class UiEvents {
      data class LogIn(val name: String?, val password: String?) : UiEvents()
    }

  }
  
}
