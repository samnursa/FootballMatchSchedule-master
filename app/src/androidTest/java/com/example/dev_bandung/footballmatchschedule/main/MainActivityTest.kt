package com.example.dev_bandung.footballmatchschedule.main

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.swipeDown
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.dev_bandung.footballmatchschedule.R.id.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testLastMatchViewBehaviour() {
        Thread.sleep(3000)
        onView(ViewMatchers.withId(listMatch))
                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withId(listMatch)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        Thread.sleep(1000)

        onView(ViewMatchers.withId(listMatch)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))
        Thread.sleep(1000)

        onView(ViewMatchers.withId(listMatch)).perform(
                    RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, ViewActions.click()))
        Thread.sleep(2000)

        onView(ViewMatchers.withId(add_to_favorite))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())
        onView(ViewMatchers.withText("Added to favorite"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(1000)

        Espresso.pressBack()
    }

    @Test
    fun testNextMatchViewBehaviour() {
        onView(ViewMatchers.withId(bottom_navigation))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withId(nextMatch)).perform(ViewActions.click())

        Thread.sleep(3000)
        onView(ViewMatchers.withId(listMatch))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withId(listMatch)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        Thread.sleep(1000)

        onView(ViewMatchers.withId(listMatch)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))
        Thread.sleep(1000)

        onView(ViewMatchers.withId(listMatch)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, ViewActions.click()))
        Thread.sleep(2000)

        onView(ViewMatchers.withId(add_to_favorite))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())
        onView(ViewMatchers.withText("Added to favorite"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(1000)

        Espresso.pressBack()
    }

    @Test
    fun testFavoriteMatchViewBehaviour() {
        onView(ViewMatchers.withId(bottom_navigation))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withId(favorites)).perform(ViewActions.click())

        Thread.sleep(3000)
        onView(ViewMatchers.withId(listMatch))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withId(listMatch)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Thread.sleep(2000)

        onView(ViewMatchers.withId(add_to_favorite))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withId(add_to_favorite)).perform(ViewActions.click())
        onView(ViewMatchers.withText("Removed to favorite"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(1000)

        Espresso.pressBack()
        Thread.sleep(2000)
        onView(ViewMatchers.withId(swipeRefresh)).perform(swipeDown())
        Thread.sleep(3000)
    }
}