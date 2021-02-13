package com.dionep.ribscage.ui.login

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dionep.ribscage.data.ApiClient
import com.dionep.ribscage.data.Prefs
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.BindsInstance
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Scope

class LoginBuilder(dependency: ParentComponent) : ViewBuilder<LoginView, LoginRouter, LoginBuilder.ParentComponent>(dependency) {

  fun build(parentViewGroup: ViewGroup): LoginRouter {
    val view = createView(parentViewGroup)
    val interactor = LoginInteractor()
    val component = DaggerLoginBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.loggedOutRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): LoginView =
    LoginView(parentViewGroup.context)

  interface ParentComponent {
    fun apiClient(): ApiClient
    fun prefs(): Prefs
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
          view: LoginView,
          interactor: LoginInteractor): LoginRouter {
        return LoginRouter(view, interactor, component)
      }

      @LoggedOutScope
      @Provides
      @JvmStatic
      internal fun feature(
        apiClient: ApiClient,
        prefs: Prefs
      ): LoginFeature = LoginFeature(apiClient, prefs)

      @LoggedOutScope
      @Provides
      @JvmStatic
      internal fun presenter(
          view: LoginView
      ): LoginInteractor.LoginPresenter = view

    }

  }

  @LoggedOutScope
  @dagger.Component(modules = [Module::class], dependencies = [ParentComponent::class])
  interface Component : InteractorBaseComponent<LoginInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: LoginInteractor): Builder

      @BindsInstance
      fun view(view: LoginView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun loggedOutRouter(): LoginRouter
  }

  @Scope
  @kotlin.annotation.Retention(AnnotationRetention.BINARY)
  internal annotation class LoggedOutScope

  @Qualifier
  @kotlin.annotation.Retention(AnnotationRetention.BINARY)
  internal annotation class LoggedOutInternal
}
