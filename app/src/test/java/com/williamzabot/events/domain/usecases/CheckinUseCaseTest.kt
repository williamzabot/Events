package com.williamzabot.events.domain.usecases

import assertk.assertThat
import assertk.assertions.isInstanceOf
import com.williamzabot.events.data.exception.BadRequestException
import com.williamzabot.events.data.exception.EmptyEmailException
import com.williamzabot.events.data.exception.EmptyNameException
import com.williamzabot.events.domain.model.CheckinBody
import com.williamzabot.events.domain.repositories.CheckinRepository
import com.williamzabot.events.domain.utils.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking


import org.junit.Test

class CheckinUseCaseTest {

    private val checkinRepository = mockk<CheckinRepository>()
    private val useCase = CheckinUseCase(checkinRepository)

    @Test
    fun `test checkin badRequestException`() {
        runBlocking {
            //given
            coEvery { checkinRepository.checkin(checkinBodyValid) } returns Result.Failure(
                BadRequestException)
            //when
            val result = useCase.execute(CheckinUseCase.Params(checkinBodyValid))
            //then
            coVerify { checkinRepository.checkin(checkinBodyValid) }
            assertThat(result).isInstanceOf(Result.Failure(BadRequestException)::class.java)
        }
    }

    @Test
    fun `test checkin with empty name`() {
        runBlocking {
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
        runBlocking {
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

    companion object {
        const val VALID_EMAIL = "teste@mail.com"
        const val INVALID_EMAIL = "ab"
        val checkinBodyValid = CheckinBody("1", "name", VALID_EMAIL)
        val checkinBodyWithEmptyEmail = CheckinBody("1", "name", "")
        val checkinBodyWithEmptyName = CheckinBody("1", "", VALID_EMAIL)

    }
}