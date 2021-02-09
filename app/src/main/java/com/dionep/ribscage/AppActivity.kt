package com.dionep.ribscage

import android.view.ViewGroup
import com.dionep.ribscage.root.RootBuilder
import com.dionep.ribscage.root.RootInteractor
import com.dionep.ribscage.root.RootRouter
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter

class AppActivity : RibActivity() {

    override fun createRouter(parentViewGroup: ViewGroup): ViewRouter<*, *, *> =
        RootBuilder(
            object : RootBuilder.ParentComponent {}
        ).build(parentViewGroup)

}