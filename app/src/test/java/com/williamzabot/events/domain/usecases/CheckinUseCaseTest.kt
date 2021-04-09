package com.williamzabot.events.domain.usecases

import assertk.assertThat
import assertk.assertions.isInstanceOf
import com.williamzabot.events.domain.exception.BadRequestException
import com.williamzabot.events.domain.exception.EmptyEmailException
import com.williamzabot.events.domain.exception.EmptyNameException
import com.williamzabot.events.domain.repositories.CheckinRepository
import com.williamzabot.events.domain.utils.Result
import com.williamzabot.events.utils.EventFactory.checkinBodyInvalid
import com.williamzabot.events.utils.EventFactory.checkinBodyValid
import com.williamzabot.events.utils.EventFactory.checkinBodyWithEmptyEmail
import com.williamzabot.events.utils.EventFactory.checkinBodyWithEmptyName
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest


import org.junit.Test

class CheckinUseCaseTest {

    private val checkinRepository = mockk<CheckinRepository>()
    private val useCase = CheckinUseCase(checkinRepository)

    @Test
    fun `test checkin success`() {
        runBlockingTest {
            //given
            coEvery { checkinRepository.checkin(checkinBodyValid) } returns Result.Success(Unit)
            //when
            val result = useCase.execute(CheckinUseCase.Params(checkinBodyValid))
            //then
            coVerify { checkinRepository.checkin(checkinBodyValid) }
            assertThat(result).isInstanceOf(Result.Success(Unit)::class.java)
        }
    }

    @Test
    fun `test checkin badRequestException`() {
        runBlockingTest {
            //given
            coEvery { checkinRepository.checkin(checkinBodyInvalid) } returns Result.Failure(
                BadRequestException)
            //when
            val result = useCase.execute(CheckinUseCase.Params(checkinBodyInvalid))
            //then
            coVerify { checkinRepository.checkin(checkinBodyInvalid) }
            assertThat(result).isInstanceOf(Result.Failure(BadRequestException)::class.java)
        }
    }

    @Test
    fun `test checkin with empty name`() {
        runBlockingTest {
            //given
            coEvery { checkinRepository.checkin(checkinBodyWithEmptyName) } returns Result.Failure(
                EmptyEmailException)
            //when
            val result = useCase.execute(CheckinUseCase.Params(checkinBodyWithEmptyName))
            //then
            coVerify { checkinRepository.checkin(checkinBodyWithEmptyName) }
            assertThat(result).isInstanceOf(Result.Failure(EmptyNameException)::class.java)
        }
    }

    @Test
    fun `test checkin with empty email`() {
        runBlockingTest {
            //given
            coEvery { checkinRepository.checkin(checkinBodyWithEmptyEmail) } returns Result.Failure(
                EmptyEmailException)
            //when
            val result = useCase.execute(CheckinUseCase.Params(checkinBodyWithEmptyEmail))
            //then
            coVerify { checkinRepository.checkin(checkinBodyWithEmptyEmail) }
            assertThat(result).isInstanceOf(Result.Failure(EmptyEmailException)::class.java)
        }
    }
}