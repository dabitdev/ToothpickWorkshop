package com.nordicloop.toothpickworkshop;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.nordicloop.mylibrary.Name;
import com.nordicloop.toothpickworkshop.binding.NameSpanishImpl;
import com.nordicloop.toothpickworkshop.binding.Surname;
import com.nordicloop.toothpickworkshop.binding.SurnameSpanishImpl;

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
public class SimpleActivityTest {
  @Rule
  public ActivityTestRule<Simple1Activity> activityRule = new ActivityTestRule<>(Simple1Activity.class, false, false);

  private Scope appScope;

  @Before
  public void setup() {
    appScope = Toothpick.openScope("ACTIVITY");
  }

  @After
  public void tearDown() {
    Toothpick.reset(appScope);
  }

  @Test
  public void testNormal() {
    activityRule.launchActivity(null);

    onView(withId(R.id.firstField))
        .check(matches(withText("stevie")));

    onView(withId(R.id.secondField))
        .check(matches(withText("wonder")));
  }

  @Test
  public void testWithTestModule() {
    appScope.installTestModules(new Module(){{
      bind(Name.class).toInstance(new NameSpanishImpl());
      bind(Surname.class).toInstance(new SurnameSpanishImpl());
    }});

    activityRule.launchActivity(null);

    onView(withId(R.id.firstField))
        .check(matches(withText("esteban")));

    onView(withId(R.id.secondField))
        .check(matches(withText("maravilla")));
  }

  @Test
  public void testWithTestModuleOnlyName() {
    appScope.installTestModules(new Module(){{
      bind(Name.class).toInstance(new NameSpanishImpl());
    }});

    activityRule.launchActivity(null);

    onView(withId(R.id.firstField))
        .check(matches(withText("esteban")));

    onView(withId(R.id.secondField))
        .check(matches(withText("wonder")));
  }
}
