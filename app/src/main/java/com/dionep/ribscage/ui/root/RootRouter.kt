package com.dionep.ribscage.ui.root

import com.dionep.ribscage.ui.login.LoginBuilder
import com.dionep.ribscage.ui.profile.ProfileBuilder
import com.dionep.ribscage.ui.profile.ProfileRouter

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
    private val loginBuilder: LoginBuilder,
    private val profileBuilder: ProfileBuilder
) : ViewRouter<RootView, RootInteractor, RootBuilder.Component>(view, interactor, component) {

    private var profileRouter: ProfileRouter? = null

    fun attachLoggedOut() {
        loginBuilder.build(view).let { router ->
            attachChild(router)
            view.addView(router.view)
        }
    }

    fun attachProfile() {
        profileBuilder.build(view).let { router ->
            profileRouter = router
            attachChild(router)
            view.addView(router.view)
        }
    }

}
