package com.williamzabot.events.detail

import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.williamzabot.events.R
import com.williamzabot.events.domain.model.Event
import com.williamzabot.events.presenter.features.detail.EventDetailFragment
import com.williamzabot.events.presenter.features.detail.EventDetailFragmentDirections
import com.williamzabot.events.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class EventDetailFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var navController: TestNavHostController
    private lateinit var event: Event

    @Before
    fun setUp() {
        navController = TestNavHostController(getApplicationContext())
        getInstrumentation().runOnMainSync { navController.setGraph(R.navigation.event_graph) }
        event = Event(1, "desc", "1", "..", 1.2, "title")
        launchFragmentInHiltContainer<EventDetailFragment>(
            fragmentArgs = bundleOf("event" to event)) {
            Navigation.setViewNavController(requireView(), navController)
        }
    }

    @Test
    fun test_components_isVisible() {
        onView(withId(R.id.button_checkin)).check(matches(isDisplayed()))
        onView(withId(R.id.description_event)).check(matches(isDisplayed()))
        onView(withId(R.id.title_event)).check(matches(isDisplayed()))
        onView(withId(R.id.image_event)).check(matches(isDisplayed()))
        onView(withId(R.id.date_event)).check(matches(isDisplayed()))
        onView(withId(R.id.price_event)).check(matches(isDisplayed()))
    }

    @Test
    fun click_button_checkin() {
        onView(withId(R.id.button_checkin)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.checkinDialogFragment)
    }

    @Test
    fun navigate_to_detail() {
        val direction = EventDetailFragmentDirections.globalActionToCheckin(event)
        getInstrumentation().runOnMainSync { navController.navigate(direction) }
        assertEquals(navController.currentDestination?.id, R.id.checkinDialogFragment)
    }
}