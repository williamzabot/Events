package com.williamzabot.events.utils

import com.williamzabot.events.domain.model.CheckinBody
import com.williamzabot.events.data.model.EventDTO
import com.williamzabot.events.domain.model.Event

object EventFactory {

    const val VALID_EMAIL = "teste@mail.com"
    const val INVALID_EMAIL = "ab"
    val checkinBodyValid = CheckinBody("1", "name", VALID_EMAIL)
    val checkinBodyInvalid = CheckinBody("", "", "")
    val checkinBodyWithEmptyEmail = CheckinBody("1", "name", "")
    val checkinBodyWithEmptyName = CheckinBody("1", "", VALID_EMAIL)
    val mockedListDTO = listOf(
        EventDTO(
            1, "description", "1", "..", 20.8, "title")
    )
    val mockedListEvent = listOf(
        Event(1, "description", "1", "..", 20.8, "title")
    )
}