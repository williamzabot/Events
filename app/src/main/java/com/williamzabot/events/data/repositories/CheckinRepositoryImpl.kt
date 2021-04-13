package com.williamzabot.events.data.repositories

import com.williamzabot.events.data.di.RetrofitModule.provideEventApi
import com.williamzabot.events.data.model.ResponseCheckinDTO
import com.williamzabot.events.domain.exception.BadRequestException
import com.williamzabot.events.domain.exception.EmptyEmailException
import com.williamzabot.events.domain.exception.EmptyNameException
import com.williamzabot.events.domain.model.CheckinBody
import com.williamzabot.events.domain.repositories.CheckinRepository
import com.williamzabot.events.domain.utils.BAD_REQUEST
import com.williamzabot.events.domain.utils.CREATED
import com.williamzabot.events.domain.utils.Result
import com.williamzabot.events.presenter.extensions.emailIsValid
import javax.inject.Inject

class CheckinRepositoryImpl @Inject constructor() : CheckinRepository {

    private val eventApi = provideEventApi()

    override suspend fun checkin(checkinBody: CheckinBody): Result<Unit> {
        val response = eventApi.checkin(checkinBody)
        return when (response.code()) {
            CREATED -> Result.Success(Unit)
            BAD_REQUEST -> {
                if (checkinBody.name.isBlank()) {
                    Result.Failure(EmptyNameException)
                } else if (checkinBody.email.isBlank()) {
                    Result.Failure(EmptyEmailException)
                } else if (!checkinBody.email.emailIsValid()) {
                    Result.Failure(EmptyEmailException)
                } else {
                    Result.Failure(BadRequestException)
                }
            }
            else -> Result.Failure(Exception())
        }

    }
}