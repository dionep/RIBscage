package com.dionep.ribscage.ui.profile

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link ProfileBuilder.ProfileScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class ProfileRouter(
    view: ProfileView,
    interactor: ProfileInteractor,
    component: ProfileBuilder.Component
) : ViewRouter<ProfileView, ProfileInteractor, ProfileBuilder.Component>(view, interactor, component)
