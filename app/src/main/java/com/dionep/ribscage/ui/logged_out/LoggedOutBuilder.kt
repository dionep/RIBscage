package com.dionep.ribscage.ui.logged_out

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dionep.ribscage.R
import com.dionep.ribscage.data.ApiClient
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Scope

class LoggedOutBuilder(dependency: ParentComponent) : ViewBuilder<LoggedOutView, LoggedOutRouter, LoggedOutBuilder.ParentComponent>(dependency) {

  fun build(parentViewGroup: ViewGroup): LoggedOutRouter {
    val view = createView(parentViewGroup)
    val interactor = LoggedOutInteractor()
    val component = DaggerLoggedOutBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.loggedOutRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): LoggedOutView =
    LoggedOutView(parentViewGroup.context)

  interface ParentComponent {
    fun apiClient(): ApiClient
  }

  @dagger.Module
  abstract class Module {

    @dagger.Module
    companion object {

      @LoggedOutScope
      @Provides
      @JvmStatic
      internal fun router(
          component: Component,
          view: LoggedOutView,
          interactor: LoggedOutInteractor): LoggedOutRouter {
        return LoggedOutRouter(view, interactor, component)
      }

      @LoggedOutScope
      @Provides
      @JvmStatic
      internal fun feature(
        apiClient: ApiClient
      ): LoggedOutFeature = LoggedOutFeature(apiClient)

      @LoggedOutScope
      @Provides
      @JvmStatic
      internal fun presenter(
        view: LoggedOutView,
        interactor: LoggedOutInteractor
      ): LoggedOutInteractor.LoggedOutPresenter = LoggedOutPresenterImpl(view, interactor)

    }

  }

  @LoggedOutScope
  @dagger.Component(modules = [Module::class], dependencies = [ParentComponent::class])
  interface Component : InteractorBaseComponent<LoggedOutInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: LoggedOutInteractor): Builder

      @BindsInstance
      fun view(view: LoggedOutView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun loggedOutRouter(): LoggedOutRouter
  }

  @Scope
  @kotlin.annotation.Retention(AnnotationRetention.BINARY)
  internal annotation class LoggedOutScope

  @Qualifier
  @kotlin.annotation.Retention(AnnotationRetention.BINARY)
  internal annotation class LoggedOutInternal
}
