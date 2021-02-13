package com.dionep.ribscage

import android.os.Bundle
import android.os.PersistableBundle
import android.view.ViewGroup
import com.dionep.ribscage.ui.root.RootBuilder
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter

class AppActivity : RibActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        App.appComponent.inject(this)
    }

    override fun createRouter(parentViewGroup: ViewGroup): ViewRouter<*, *, *> =
        RootBuilder(App.appComponent).build(parentViewGroup)

}