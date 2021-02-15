package com.dionep.ribscage.ui.register

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dionep.ribscage.data.ApiClient
import com.dionep.ribscage.ui.root.RootRouter
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.CLASS
import javax.inject.Qualifier
import javax.inject.Scope

class RegisterBuilder(dependency: ParentComponent) : ViewBuilder<RegisterView, RegisterRouter, RegisterBuilder.ParentComponent>(dependency) {

  fun build(parentViewGroup: ViewGroup): RegisterRouter {
    val view = createView(parentViewGroup)
    val interactor = RegisterInteractor()
    val component = DaggerRegisterBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.registerRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): RegisterView =
      RegisterView(parentViewGroup.context)

  interface ParentComponent {
    fun apiClient(): ApiClient
    fun rootRouter(): RootRouter
  }

  @dagger.Module
  abstract class Module {

    @RegisterScope
    @Binds
    internal abstract fun presenter(view: RegisterView): RegisterInteractor.RegisterPresenter

    @dagger.Module
    companion object {

      @RegisterScope
      @Provides
      @JvmStatic
      internal fun router(
          component: Component,
          view: RegisterView,
          interactor: RegisterInteractor): RegisterRouter {
        return RegisterRouter(view, interactor, component)
      }

      @RegisterScope
      @Provides
      @JvmStatic
      internal fun feature(
          apiClient: ApiClient
      ) = RegisterFeature(apiClient)

    }

  }

  @RegisterScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<RegisterInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: RegisterInteractor): Builder

      @BindsInstance
      fun view(view: RegisterView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun registerRouter(): RegisterRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class RegisterScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class RegisterInternal
}
