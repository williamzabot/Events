package com.williamzabot.events.data.repositories

import com.williamzabot.events.data.di.RetrofitModule.provideEventApi
import com.williamzabot.events.domain.model.Event
import com.williamzabot.events.domain.repositories.EventRepository
import com.williamzabot.events.domain.utils.Result
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(): EventRepository {

    private val eventApi = provideEventApi()

    override suspend fun getEvents(): Result<Event> {
        val response = eventApi.getEvents()
        return when (response.code()) {
            200 -> Result.Success(response.body()!!)
            else -> Result.Failure(Exception())
        }
    }
}