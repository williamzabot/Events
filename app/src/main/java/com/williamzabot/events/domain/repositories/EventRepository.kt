package com.williamzabot.events.domain.repositories

import com.williamzabot.events.data.model.EventDTO
import com.williamzabot.events.domain.utils.Result

interface EventRepository {
    suspend fun getEvents() : Result<List<EventDTO>>
}