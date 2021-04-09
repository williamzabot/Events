package com.williamzabot.events.domain.model

import java.io.Serializable

data class EventItem(
    val date: Long,
    val description: String,
    val id: String,
    val image: String,
    val price: Double,
    val title: String
) : Serializable