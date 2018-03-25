package com.nordicloop.toothpickworkshop;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.nordicloop.mylibrary.FullName;
import com.nordicloop.mylibrary.LibraryScope;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import toothpick.Toothpick;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class Library2ActivityTest {
  @Rule
  public ActivityTestRule<Library2Activity> activityRule = new ActivityTestRule<>(Library2Activity.class, false, false);

  @Before
  public void setup() {
    Toothpick.reset();
    LibraryScope.getOrCreateScope().release();
  }

  @After
  public void tearDown() {
    LibraryScope.getOrCreateScope().release();
    Toothpick.reset();
  }

  @Test
  public void testNormal() {
    activityRule.launchActivity(null);

    onView(withId(R.id.firstField))
        .check(matches(withText("stephen hawking")));
  }

  @Test
  public void testChildScope() {
    FullName fullname = LibraryScope.getOrCreateScope().getScope().getInstance(FullName.class);

    Library2Activity libraryActivity = activityRule.launchActivity(null);

    assertEquals(fullname, libraryActivity.mFullName);

    libraryActivity.installChildScope();

    assertEquals("lionel messi", libraryActivity.mFullName.getFullName());

    FullName fullname2 = LibraryScope.getOrCreateScope().getScope().getInstance(FullName.class);

    assertNotEquals(fullname2, libraryActivity.mFullName);
  }
}