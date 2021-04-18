package com.example.futsalapp

import androidx.test.espresso.Espresso.closeSoftKeyboard
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
class RegistrationTest {
    @get:Rule
    val testRule = ActivityScenarioRule(SignupActivity::class.java)

    @Test
    fun testSignupUI(){
        onView(withId(R.id.fName))
                .perform(typeText("firstname"))
        onView(withId(R.id.lName))
                .perform(typeText("lastname"))
        onView(withId(R.id.uName))
                .perform(typeText("username"))
        onView(withId(R.id.pass))
                .perform(typeText("passcode"))

        closeSoftKeyboard()
        onView(withId(R.id.btnSignup))
                .perform(ViewActions.click())

        Thread.sleep(2000)


    }
}