package com.dionep.ribscage.ui.register

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link RegisterBuilder.RegisterScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class RegisterRouter(
    view: RegisterView,
    interactor: RegisterInteractor,
    component: RegisterBuilder.Component) : ViewRouter<RegisterView, RegisterInteractor, RegisterBuilder.Component>(view, interactor, component)
