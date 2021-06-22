package com.example.futsalapp

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)
class UserUpdateTest {
    @get:Rule
    val testRule = ActivityScenarioRule(UserupdateActivity::class.java)

    @Test
    fun testUserUpdateUI(){
        onView(withId(R.id.etFirstname))
                .perform(typeText("Saurav"))
        onView(withId(R.id.etLastname))
                .perform(typeText("Parajuli"))
        onView(withId(R.id.etUsername))
                .perform(typeText("saurav1"))
        onView(withId(R.id.etEmail))
                .perform(typeText("saurav@gmail.com"))
        onView(withId(R.id.etPhone))
                .perform(typeText("9860674940"))
        onView(withId(R.id.etAddress))
                .perform(typeText("Kathmandu"))
        onView(withId(R.id.etAge))
                .perform(typeText("21"))

        Espresso.closeSoftKeyboard()

        onView(withId(R.id.btnUpdate))
                .perform(ViewActions.click())

        Thread.sleep(2000)

    }
}