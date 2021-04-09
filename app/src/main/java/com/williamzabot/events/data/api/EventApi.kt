package com.williamzabot.events.data.api

import com.williamzabot.events.domain.model.CheckinBody
import com.williamzabot.events.data.model.EventDTO
import com.williamzabot.events.data.model.ResponseCheckinDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EventApi {

    @GET("events")
    suspend fun getEvents(): Response<List<EventDTO>>

    @POST("checkin")
    suspend fun checkin(@Body checkinBody: CheckinBody): Response<ResponseCheckinDTO>
}