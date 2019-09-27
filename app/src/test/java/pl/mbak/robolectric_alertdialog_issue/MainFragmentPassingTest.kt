package pl.mbak.robolectric_alertdialog_issue

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import org.robolectric.shadows.ShadowAlertDialog

@RunWith(AndroidJUnit4::class)
@LooperMode(LooperMode.Mode.PAUSED)
@Config(application = MainFragmentPassingTest.TestApp::class, sdk = [28])
class MainFragmentPassingTest {

  private lateinit var fragment: MainPassingFragment
  private lateinit var context: Context
  private lateinit var factory: FragmentFactory

  @Before
  fun setUp() {
    context = ApplicationProvider.getApplicationContext<Application>()
    fragment = MainPassingFragment()

    factory = object : FragmentFactory() {
      override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return fragment
      }
    }
  }

  @Test
  fun `alert dialog is shown aften label clicked`() {
    // Having
    val scenario: FragmentScenario<MainPassingFragment> = launchFragmentInContainer(factory = factory)

    // When
    scenario.moveToState(Lifecycle.State.RESUMED)
    Espresso.onView(ViewMatchers.withId(R.id.textView)).perform(ViewActions.click())

    // Then
    (ShadowAlertDialog.getLatestDialog() as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE).performClick()
  }

  class TestApp : Application() {
    override fun onCreate() {
      super.onCreate()
      setTheme(R.style.AppTheme)
    }
  }
}