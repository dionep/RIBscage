package com.dionep.ribscage.ui.register

import android.view.LayoutInflater
import android.view.ViewGroup
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.CLASS
import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Builder for the {@link RegisterScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class RegisterBuilder(dependency: ParentComponent) : ViewBuilder<RegisterView, RegisterRouter, RegisterBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [RegisterRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [RegisterRouter].
   */
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

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): RegisterView? {
    // TODO: Inflate a new view using the provided inflater, or create a new view programatically using the
    // provided context from the parentViewGroup.
    return null
  }

  interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
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
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
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
