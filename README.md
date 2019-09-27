# robolectric-alertdialog-issue

Project presenting tests issue with *Robolectric 4.3* and *androidx.appcompat.app.AlertDialog* shown from within a Fragment run using *androidx.fragment.app.testing.launchFragmentInContainer*.

# Problem

To see the error run:

```
./gradlew testDebug --tests pl.mbak.robolectric_alertdialog_issue.MainFragmentFailingTest
```

Following tests are passing without such problem:
Class `pl.mbak.robolectric_alertdialog_issue.MainFragmentPassingTest` is testing a `MainPassingFragment` that uses `android.app.AltertDialog`. 
Class `pl.mbak.robolectric_alertdialog_issue.MainActivityTest` even though it uses  `androidx.appcompat.app.AlertDialog`.

# Solution

Instantiating `FragmentScenario` with proper `themeResId` fixes the issue.
```kotlin
val scenario: FragmentScenario<MainFailingFragment> = launchFragmentInContainer(themeResId = R.style.MaterialAppTheme, factory = factory)
``` 
