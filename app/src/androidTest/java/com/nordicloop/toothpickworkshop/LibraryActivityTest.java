package com.nordicloop.toothpickworkshop;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.nordicloop.mylibrary.Singleton;
import com.nordicloop.toothpickworkshop.bindings.Name;
import com.nordicloop.toothpickworkshop.bindings.NameSpanishImpl;
import com.nordicloop.toothpickworkshop.bindings.Surname;
import com.nordicloop.toothpickworkshop.bindings.SurnameSpanishImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import toothpick.Scope;
import toothpick.Toothpick;
import toothpick.config.Module;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LibraryActivityTest {
  @Rule
  public ActivityTestRule<LibraryActivity> activityRule = new ActivityTestRule<>(LibraryActivity.class, false, false);

  @Before
  public void setup() {
    Singleton.release();
  }

  @After
  public void tearDown() {
    Singleton.release();
  }

  @Test
  public void testNormal() {
    activityRule.launchActivity(null);

    onView(withId(R.id.firstField))
        .check(matches(withText("stephen hawking")));
  }
}