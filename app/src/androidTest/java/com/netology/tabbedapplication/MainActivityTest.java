package com.netology.tabbedapplication;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    final String firstTabDescription = "Tab 1";
    final String secondTabDescription = "Tab 2";
    final String textOnSectionLabelFirstFragment = "Page: 1";
    final String textOnSectionLabelSecondFragment = "Page: 2";

    @Test
    public void mainActivityTest() {
        ViewInteraction firstTabVew = onView(withContentDescription(firstTabDescription));
        firstTabVew.check(matches(allOf(isDisplayed(), isSelected())));

        ViewInteraction secondTabView = onView(withContentDescription(secondTabDescription));
        secondTabView.check(matches(isDisplayed()));

        onView(allOf(withId(R.id.section_label), isDisplayed()))
                .check(matches(withText(textOnSectionLabelFirstFragment)));

        secondTabView.perform(click());
        secondTabView.check(matches(isSelected()));

        onView(allOf(withId(R.id.section_label), isDisplayed()))
                .check(matches(withText(textOnSectionLabelSecondFragment)));

        firstTabVew.perform(click());
        firstTabVew.check(matches(isSelected()));

        onView(allOf(withId(R.id.section_label), isDisplayed()))
                .check(matches(withText(textOnSectionLabelFirstFragment)));
    }
}
