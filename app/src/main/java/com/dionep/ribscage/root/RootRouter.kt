package com.dionep.ribscage.root

import android.view.View
import com.dionep.ribscage.logged_out.LoggedOutBuilder

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link RootBuilder.RootScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class RootRouter(
    view: RootView,
    interactor: RootInteractor,
    component: RootBuilder.Component,
    private val loggedOutBuilder: LoggedOutBuilder
) : ViewRouter<RootView, RootInteractor, RootBuilder.Component>(view, interactor, component) {

    fun attachLoggedOut() {
        loggedOutBuilder.build(view).let { router ->
            attachChild(router)
            view.addView(router.view)
        }
    }

}
