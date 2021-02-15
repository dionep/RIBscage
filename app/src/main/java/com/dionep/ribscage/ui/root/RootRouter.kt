package com.dionep.ribscage.ui.root

import com.dionep.ribscage.ui.login.LoginBuilder
import com.dionep.ribscage.ui.login.LoginRouter
import com.dionep.ribscage.ui.profile.ProfileBuilder
import com.dionep.ribscage.ui.profile.ProfileRouter
import com.dionep.ribscage.ui.register.RegisterBuilder
import com.dionep.ribscage.ui.register.RegisterRouter

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
    private val profileBuilder: ProfileBuilder,
    private val registerBuilder: RegisterBuilder
) : ViewRouter<RootView, RootInteractor, RootBuilder.Component>(view, interactor, component) {

    private var profileRouter: ProfileRouter? = null
    private var loginRouter: LoginRouter? = null
    private var registerRouter: RegisterRouter? = null

    fun attachLogin() {
        loginBuilder.build(view).let { router ->
            profileRouter?.let {
                detachChild(it)
                view.removeView(it.view)
                profileRouter = null
            }
            loginRouter = router
            attachChild(router)
            view.addView(router.view)
        }
    }

    fun attachProfile() {
        profileBuilder.build(view).let { router ->
            loginRouter?.let {
                detachChild(it)
                view.removeView(it.view)
                loginRouter = null
            }
            registerRouter?.let {
                detachChild(it)
                view.removeView(it.view)
                registerRouter = null
            }
            profileRouter = router
            attachChild(router)
            view.addView(router.view)
        }
    }

    fun attachRegister() {
        registerBuilder.build(view).let { router ->
            loginRouter?.let {
                detachChild(it)
                view.removeView(it.view)
                loginRouter = null
            }
            registerRouter = router
            attachChild(router)
            view.addView(router.view)
        }
    }

}
