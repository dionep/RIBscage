package com.dionep.ribscage.ui.root

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dionep.ribscage.R
import com.dionep.ribscage.data.ApiClient
import com.dionep.ribscage.data.Prefs
import com.dionep.ribscage.ui.login.LoginBuilder
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Scope

class RootBuilder(dependency: ParentComponent) : ViewBuilder<RootView, RootRouter, RootBuilder.ParentComponent>(dependency) {

  fun build(parentViewGroup: ViewGroup): RootRouter {
    val view = createView(parentViewGroup)
    val interactor = RootInteractor()
    val component = DaggerRootBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.rootRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): RootView =
    inflater.inflate(R.layout.rib_root, parentViewGroup, false) as RootView

  interface ParentComponent {
    fun apiClient(): ApiClient
    fun prefs(): Prefs
  }

  @dagger.Module
  abstract class Module {

    @RootScope
    @Binds
    internal abstract fun presenter(view: RootView): RootInteractor.RootPresenter

    @dagger.Module
    companion object {

      @RootScope
      @Provides
      @JvmStatic
      internal fun router(
          component: Component,
          view: RootView,
          interactor: RootInteractor
      ): RootRouter {
        return RootRouter(view, interactor, component, LoginBuilder(component))
      }
    }

  }

  @RootScope
  @dagger.Component(
    modules = [Module::class],
    dependencies = [ParentComponent::class]
  )
  interface Component :
    InteractorBaseComponent<RootInteractor>,
    LoginBuilder.ParentComponent,
    BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: RootInteractor): Builder

      @BindsInstance
      fun view(view: RootView): Builder

      fun parentComponent(component: ParentComponent): Builder

      fun build(): Component
    }



  }

  interface BuilderComponent {
    fun rootRouter(): RootRouter
  }

  @Scope
  @kotlin.annotation.Retention(AnnotationRetention.BINARY)
  internal annotation class RootScope

  @Qualifier
  @kotlin.annotation.Retention(AnnotationRetention.BINARY)
  internal annotation class RootInternal
}
