package com.dionep.ribscage.base

import kotlinx.coroutines.flow.SharedFlow

interface BasePresenter<UiEvents, State> {
  
  fun uiEvents(): SharedFlow<UiEvents>
  fun renderState(state: State)
  fun showMessage(message: String)

}