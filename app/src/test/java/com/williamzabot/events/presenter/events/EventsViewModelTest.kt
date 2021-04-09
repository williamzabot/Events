package com.williamzabot.events.presenter.events

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.williamzabot.events.domain.usecases.EventUseCase
import com.williamzabot.events.domain.utils.Result
import com.williamzabot.events.presenter.CoroutinesTestRule
import com.williamzabot.events.presenter.features.events.EventsViewModel
import com.williamzabot.events.utils.EventFactory.mockedListEvent
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EventsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var viewModel: EventsViewModel
    private val useCase = mockk<EventUseCase>()

    @Before
    fun setUp() {
        viewModel = EventsViewModel(useCase)
    }

    @Test
    fun `test checkin success `() {
        //given
        coEvery { useCase.execute() } returns Result.Success(mockedListEvent)
        //when
        viewModel.getEvents()
        //then
        assertThat(viewModel.events.value).isEqualTo(mockedListEvent)
    }

}
