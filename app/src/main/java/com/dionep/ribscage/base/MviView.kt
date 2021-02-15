package com.dionep.ribscage.base

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

abstract class MviView<UiEvents, State> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), BasePresenter<UiEvents, State> {

    private val eventsFlow = MutableSharedFlow<UiEvents>(extraBufferCapacity = 64)

    override fun uiEvents(): SharedFlow<UiEvents> = eventsFlow

    fun acceptEvent(event: UiEvents) {
        eventsFlow.tryEmit(event)
    }

    override fun showMessage(message: String) {
        Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
    }

}