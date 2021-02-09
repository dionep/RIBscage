package com.dionep.ribscage.root

import com.uber.rib.core.Bundle
import com.uber.rib.core.Interactor
import com.uber.rib.core.RibInteractor
import javax.inject.Inject

@RibInteractor
class RootInteractor : Interactor<RootInteractor.RootPresenter, RootRouter>() {

  @Inject
  lateinit var presenter: RootPresenter

  override fun didBecomeActive(savedInstanceState: Bundle?) {
    super.didBecomeActive(savedInstanceState)
    router.attachLoggedOut()
  }

  override fun willResignActive() {
    super.willResignActive()

    // TODO: Perform any required clean up here, or delete this method entirely if not needed.
  }

  interface RootPresenter

}
