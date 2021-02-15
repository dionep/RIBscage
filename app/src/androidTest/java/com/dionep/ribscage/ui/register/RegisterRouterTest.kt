package com.dionep.ribscage.ui.register

import com.uber.rib.core.RibTestBasePlaceholder
import com.uber.rib.core.RouterHelper

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class RegisterRouterTest : RibTestBasePlaceholder() {

  @Mock internal lateinit var component: RegisterBuilder.Component
  @Mock internal lateinit var interactor: RegisterInteractor
  @Mock internal lateinit var view: RegisterView

  private var router: RegisterRouter? = null

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)

    router = RegisterRouter(view, interactor, component)
  }

  /**
   * TODO: Delete this example and add real tests.
   */
  @Test
  fun anExampleTest_withSomeConditions_shouldPass() {
    // Use RouterHelper to drive your router's lifecycle.
    RouterHelper.attach(router!!)
    RouterHelper.detach(router!!)

    throw RuntimeException("Remove this test and add real tests.")
  }

}

