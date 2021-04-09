package com.williamzabot.events.domain.usecases

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.williamzabot.events.domain.repositories.EventRepository
import com.williamzabot.events.domain.utils.Result
import com.williamzabot.events.utils.EventFactory.mockedListDTO
import com.williamzabot.events.utils.EventFactory.mockedListEvent
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class EventUseCaseTest {

    private val repository = mockk<EventRepository>()
    private val useCase = EventUseCase(repository)

    @Test
    fun `when getEvents return success`() {
        runBlockingTest {
            //given
            val expected = Result.Success(mockedListEvent)
            coEvery { repository.getEvents() } returns Result.Success(mockedListDTO)

            //when
            val result = useCase.execute()

            //then
            assertThat(result).isEqualTo(expected)
        }
    }

}