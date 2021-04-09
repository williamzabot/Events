package com.williamzabot.events.domain.repositories

import com.williamzabot.events.domain.model.CheckinBody
import com.williamzabot.events.domain.utils.Result

interface CheckinRepository {
    suspend fun checkin(checkinBody: CheckinBody) : Result<Unit>
}