package com.williamzabot.events.domain.usecases

import com.williamzabot.events.domain.utils.Result

abstract class BaseUseCase<T : Any, in Params> {
    abstract suspend fun execute(params: Params? = null): Result<T>
}