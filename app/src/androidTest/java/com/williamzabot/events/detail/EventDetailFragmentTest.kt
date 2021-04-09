package com.williamzabot.events.detail

import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import androidx.test.platform.app.InstrumentationRegistry
import com.williamzabot.events.R
import com.williamzabot.events.domain.model.Event
import com.williamzabot.events.presenter.features.detail.EventDetailFragment
import com.williamzabot.events.presenter.features.detail.EventDetailFragmentDirections
import com.williamzabot.events.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
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
        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            navController.setGraph(R.navigation.event_graph)
        }
        event = Event(1, "desc", "1", "..", 1.2, "title")
        launchFragmentInHiltContainer<EventDetailFragment>(fragmentArgs = bundleOf("event" to event)) {
            Navigation.setViewNavController(requireView(), navController)
        }
    }

    @Test
    fun test_components_isVisible() {
        Espresso.onView(withId(R.id.button_checkin))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun click_button_checkin() {
        Espresso.onView(withId(R.id.button_checkin)).perform(ViewActions.click())
        Assert.assertEquals(navController.currentDestination?.id, R.id.checkinDialogFragment)
    }

    @Test
    fun navigate_to_detail() {
        val direction = EventDetailFragmentDirections.globalActionToCheckin(event)
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            navController.navigate(direction)
        }
        Assert.assertEquals(navController.currentDestination?.id, R.id.checkinDialogFragment)
    }
}