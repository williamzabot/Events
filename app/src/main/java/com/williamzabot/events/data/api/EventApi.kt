package com.williamzabot.events.data.api

import com.williamzabot.events.domain.model.CheckinBody
import com.williamzabot.events.domain.model.Event
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EventApi {

    @GET("events")
    suspend fun getEvents(): Response<Event>

    @POST("checkin")
    suspend fun checkin(
        @Body checkinBody: CheckinBody,
    ): Response<Void>
}