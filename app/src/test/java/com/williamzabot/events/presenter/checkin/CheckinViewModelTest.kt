package com.williamzabot.events.presenter.checkin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.williamzabot.events.data.exception.BadRequestException
import com.williamzabot.events.domain.usecases.CheckinUseCase
import com.williamzabot.events.domain.usecases.CheckinUseCaseTest.Companion.checkinBodyValid
import com.williamzabot.events.domain.utils.Result
import com.williamzabot.events.presenter.CoroutinesTestRule
import com.williamzabot.events.presenter.features.checkin.CheckinViewModel
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CheckinViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private lateinit var viewModel: CheckinViewModel
    private val useCase = mockk<CheckinUseCase>()

    @Before
    fun setUp() {
        viewModel = CheckinViewModel(useCase)
    }

    @Test
    fun `test checkin `() {
        //given
        coEvery { useCase.execute(CheckinUseCase.Params(checkinBodyValid)) } returns Result.Failure(BadRequestException)
        //when
        viewModel.checkin(checkinBodyValid.eventId, checkinBodyValid.name, checkinBodyValid.email)
        //then
        assertThat(viewModel.errorAPI.value).isEqualTo(true)
    }
}