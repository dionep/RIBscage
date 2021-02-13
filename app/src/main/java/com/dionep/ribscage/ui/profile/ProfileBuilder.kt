package com.dionep.ribscage.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dionep.ribscage.data.ApiClient
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Builder for the {@link ProfileScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class ProfileBuilder(dependency: ParentComponent) : ViewBuilder<ProfileView, ProfileRouter, ProfileBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [ProfileRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [ProfileRouter].
   */
  fun build(parentViewGroup: ViewGroup): ProfileRouter {
    val view = createView(parentViewGroup)
    val interactor = ProfileInteractor()
    val component = DaggerProfileBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.profileRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): ProfileView =
      ProfileView(parentViewGroup.context)

  interface ParentComponent {
    fun apiClient(): ApiClient
  }

  @dagger.Module
  abstract class Module {

    @ProfileScope
    @Binds
    internal abstract fun presenter(view: ProfileView): ProfileInteractor.ProfilePresenter

    @dagger.Module
    companion object {

      @ProfileScope
      @Provides
      @JvmStatic
      internal fun router(
          component: Component,
          view: ProfileView,
          interactor: ProfileInteractor): ProfileRouter {
        return ProfileRouter(view, interactor, component)
      }

      @ProfileScope
      @Provides
      @JvmStatic
      internal fun feature(
          apiClient: ApiClient
      ): ProfileFeature = ProfileFeature(apiClient)
    }

  }

  @ProfileScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<ProfileInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: ProfileInteractor): Builder

      @BindsInstance
      fun view(view: ProfileView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun profileRouter(): ProfileRouter
  }

  @Scope
  @kotlin.annotation.Retention(AnnotationRetention.BINARY)
  internal annotation class ProfileScope

  @Qualifier
  @kotlin.annotation.Retention(AnnotationRetention.BINARY)
  internal annotation class ProfileInternal
}
