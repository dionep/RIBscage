package com.dionep.ribscage.ui.login

import com.dionep.ribscage.ui.login.LoginInteractor.*
import com.dionep.ribscage.ui.login.LoginInteractor.LoggedOutPresenter.*

class LoginPresenterImpl(
    view: LoginView,
    interactor: LoginInteractor
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