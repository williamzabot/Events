package com.williamzabot.events.domain.repositories

import com.williamzabot.events.domain.model.Event
import com.williamzabot.events.domain.utils.Result

interface EventRepository {
    suspend fun getEvents() : Result<Event>
}