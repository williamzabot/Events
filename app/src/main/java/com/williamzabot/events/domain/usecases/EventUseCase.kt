package com.williamzabot.events.domain.usecases

import com.williamzabot.events.domain.model.EventItem
import com.williamzabot.events.domain.repositories.EventRepository
import com.williamzabot.events.domain.utils.Result
import javax.inject.Inject

class EventUseCase @Inject constructor(private val eventRepository: EventRepository) :
        BaseUseCase<List<EventItem>, EventUseCase.Params>() {

    class Params()

    override suspend fun execute(params: Params?): Result<List<EventItem>> {
        return when (val result = eventRepository.getEvents()) {
            is Result.Success -> Result.Success(result.data)
            is Result.Failure -> Result.Failure(Exception())
        }
    }
}