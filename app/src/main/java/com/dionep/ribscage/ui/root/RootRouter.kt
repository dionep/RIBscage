package com.dionep.ribscage.ui.root

import com.dionep.ribscage.ui.login.LoginBuilder

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
    private val loggedOutBuilder: LoginBuilder
) : ViewRouter<RootView, RootInteractor, RootBuilder.Component>(view, interactor, component) {

    fun attachLoggedOut() {
        loggedOutBuilder.build(view).let { router ->
            attachChild(router)
            view.addView(router.view)
        }
    }

}
