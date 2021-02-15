package com.dionep.ribscage.base

import kotlinx.coroutines.flow.SharedFlow

interface BasePresenter<UiEvents> {
  
  fun uiEvents(): SharedFlow<UiEvents>
  fun showMessage(message: String)

}