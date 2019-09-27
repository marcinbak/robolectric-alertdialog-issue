package pl.mbak.robolectric_alertdialog_issue

import android.app.Application
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import org.robolectric.shadows.ShadowAlertDialog

@RunWith(AndroidJUnit4::class)
@LooperMode(LooperMode.Mode.PAUSED)
@Config(application = MainActivityTest.TestApp::class, sdk = [28])
class MainActivityTest {

  @get:Rule val activityScenarioRule = ActivityScenarioRule<MainActivity>(MainActivity::class.java)

  @Test
  fun `check fragment label cliked shows alert dialog`() {
    // Having
    val scenario = activityScenarioRule.scenario
    scenario.moveToState(Lifecycle.State.RESUMED)

    // When
    Espresso.onView(ViewMatchers.withId(R.id.textView)).perform(ViewActions.click())

    // Then
    (ShadowAlertDialog.getLatestDialog() as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE).performClick()
  }

  class TestApp : Application() {
    override fun onCreate() {
      super.onCreate()
      setTheme(R.style.MaterialAppTheme)
    }
  }
}