package com.williamzabot.events.domain.utils

import com.williamzabot.events.domain.model.EventItem

object EventFactory {

    val events = listOf(
        EventItem(
            10, "desc", "1", "..", 20.1, "title"
        ),
        EventItem(
            10, "desc", "2", "..", 20.1, "title"
        ),
        EventItem(
            10, "desc", "3", "..", 20.1, "title"
        ),
        EventItem(
            10, "desc", "4", "..", 20.1, "title"
        )
    )
}