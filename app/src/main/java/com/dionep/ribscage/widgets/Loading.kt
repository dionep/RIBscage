package com.dionep.ribscage.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.dionep.ribscage.databinding.WidgetLoadingBinding

class Loading @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attributeSet, defStyle) {

    init {
        WidgetLoadingBinding.inflate(LayoutInflater.from(context), this, true)
    }

}