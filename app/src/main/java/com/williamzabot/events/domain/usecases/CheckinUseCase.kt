package com.williamzabot.events.domain.usecases

import com.williamzabot.events.domain.model.CheckinBody
import com.williamzabot.events.domain.repositories.CheckinRepository
import com.williamzabot.events.domain.utils.Result
import javax.inject.Inject

class CheckinUseCase @Inject constructor(private val checkinRepository: CheckinRepository) :
    BaseUseCase<Unit, CheckinUseCase.Params>() {

    data class Params(val checkinBody: CheckinBody)

    override suspend fun execute(params: Params?): Result<Unit> {
        if (params == null) throw IllegalArgumentException()
        return when (val result = checkinRepository.checkin(params.checkinBody)) {
            is Result.Success -> Result.Success(Unit)
            is Result.Failure -> Result.Failure(result.exception)
        }
    }
}