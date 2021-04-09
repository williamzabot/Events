package com.williamzabot.events.domain.usecases

import com.williamzabot.events.data.model.toEvent
import com.williamzabot.events.domain.model.Event
import com.williamzabot.events.domain.repositories.EventRepository
import com.williamzabot.events.domain.utils.Result
import javax.inject.Inject

class EventUseCase @Inject constructor(private val eventRepository: EventRepository) {

    suspend fun execute(): Result<List<Event>> {
        return when (val result = eventRepository.getEvents()) {
            is Result.Success -> Result.Success(result.data.map { eventDTO ->
                eventDTO.toEvent()
            })
            is Result.Failure -> Result.Failure(Exception())
        }
    }
}