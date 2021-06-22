package com.example.futsalapp

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import org.hamcrest.CoreMatchers.`is`
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)
class FutsalBookTest {

    @get:Rule
    val testRule = ActivityScenarioRule(FutsaldetailActivity::class.java)


    @Test
    fun testBookingUI(){
        onView(withId(R.id.etDate))
                .perform(typeText("02/05/2021"))

        val mytime = arrayOf("06 am", "07 am", "08 am")
        val size1 : Int = mytime.size

        for(i in 0 until size1){
            onView(withId(R.id.spinnerTime)).perform(click())
            onData(`is`(mytime[i])).perform(click())
        }

        closeSoftKeyboard()

        onView(withId(R.id.btnBook))
                .perform(click())

        Thread.sleep(2000)



    }

}