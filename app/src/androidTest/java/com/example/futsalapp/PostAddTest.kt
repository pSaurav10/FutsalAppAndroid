package com.example.futsalapp

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)
class PostAddTest {
    @get:Rule
    val testRule = ActivityScenarioRule(AddpostActivity::class.java)

    @Test
    fun testPostUI(){
        onView(withId(R.id.etPost))
                .perform(ViewActions.typeText("This is a post"))

        closeSoftKeyboard()
        onView(withId(R.id.btnadd))
                .perform(ViewActions.click())

        Thread.sleep(2000)
    }
}