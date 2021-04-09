package com.williamzabot.events.domain.model

import java.io.Serializable

data class Event(
    val date: Long,
    val description: String,
    val id: String,
    val image: String,
    val price: Double,
    val title: String
) : Serializable