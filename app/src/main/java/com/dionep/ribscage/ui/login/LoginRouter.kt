package com.dionep.ribscage.ui.login

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link LoggedOutBuilder.LoggedOutScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class LoginRouter(
    view: LoginView,
    interactor: LoginInteractor,
    component: LoginBuilder.Component) : ViewRouter<LoginView, LoginInteractor, LoginBuilder.Component>(view, interactor, component)
