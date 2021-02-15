package com.dionep.ribscage.ui.root

import android.view.View
import com.dionep.ribscage.ui.login.LoginBuilder
import com.dionep.ribscage.ui.login.LoginRouter
import com.dionep.ribscage.ui.profile.ProfileBuilder
import com.dionep.ribscage.ui.profile.ProfileRouter
import com.dionep.ribscage.ui.register.RegisterBuilder
import com.dionep.ribscage.ui.register.RegisterRouter

import com.uber.rib.core.ViewRouter


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
                it.view.animateDisappearing { view.removeView(it) }
                profileRouter = null
            }
            registerRouter?.let {
                detachChild(it)
                it.view.animateDisappearing { view.removeView(it) }
                registerRouter = null
            }
            loginRouter = router
            attachChild(router)
            view.addView(router.view.animateAppearing())
        }
    }

    fun attachProfile() {
        profileBuilder.build(view).let { router ->
            loginRouter?.let {
                detachChild(it)
                it.view.animateDisappearing { view.removeView(it) }
                loginRouter = null
            }
            registerRouter?.let {
                detachChild(it)
                it.view.animateDisappearing { view.removeView(it) }
                registerRouter = null
            }
            profileRouter = router
            attachChild(router)
            view.addView(router.view.animateAppearing())
        }
    }

    fun attachRegister() {
        registerBuilder.build(view).let { router ->
            loginRouter?.let {
                detachChild(it)
                it.view.animateDisappearing { view.removeView(it) }
                loginRouter = null
            }
            registerRouter = router
            attachChild(router)
            view.addView(router.view.animateAppearing())
        }
    }

    fun View.animateAppearing(): View = this.apply {
        alpha = 0f
        animate()
            .setDuration(500)
            .alpha(1f)
    }

    fun View.animateDisappearing(
        endWithAction: (View) -> Unit
    ): View = this.apply {
        alpha = 1f
        animate()
            .setDuration(500)
            .alpha(0f)
            .withEndAction { endWithAction.invoke(this) }
    }

}
