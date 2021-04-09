package com.williamzabot.events.domain.usecases

import com.williamzabot.events.domain.repositories.EventRepository
import io.mockk.mockk

class EventUseCaseTest {

    private val repository = mockk<EventRepository>()
    private val useCase = EventUseCase(repository)




}