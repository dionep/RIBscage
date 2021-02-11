package com.dionep.ribscage.ui.logged_out

import com.dionep.ribscage.ui.logged_out.LoggedOutInteractor.*
import com.dionep.ribscage.ui.logged_out.LoggedOutInteractor.LoggedOutPresenter.*

class LoggedOutPresenterImpl(
    view: LoggedOutView,
    interactor: LoggedOutInteractor
): LoggedOutPresenter {

    init {
        view.setOnLoginClicked { name, pass ->
            interactor.acceptUiEvent(
                UiEvents.LogIn(
                    name = name,
                    password = pass
                )
            )
        }
    }

}