package com.williamzabot.events.data.repositories

import com.williamzabot.events.data.di.RetrofitModule.provideEventApi
import com.williamzabot.events.data.model.EventDTO
import com.williamzabot.events.domain.repositories.EventRepository
import com.williamzabot.events.domain.utils.Result
import com.williamzabot.events.domain.utils.SUCCESS
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor() : EventRepository {

    private val eventApi = provideEventApi()

    override suspend fun getEvents(): Result<List<EventDTO>> {
        val response = eventApi.getEvents()
        return when (response.code()) {
            SUCCESS -> Result.Success(response.body()!!)
            else -> Result.Failure(Exception())
        }
    }
}

