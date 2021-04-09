package com.williamzabot.events.data.model

import com.williamzabot.events.domain.model.Event

data class EventDTO(
    val date: Long,
    val description: String,
    val id: String,
    val image: String,
    val price: Double,
    val title: String,
)

fun EventDTO.toEvent(): Event {
    return Event(
        date, description, id, image, price, title
    )
}