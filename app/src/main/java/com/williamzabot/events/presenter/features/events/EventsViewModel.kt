package com.williamzabot.events.presenter.features.events

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.williamzabot.events.domain.exception.BadRequestException
import com.williamzabot.events.domain.model.Event
import com.williamzabot.events.domain.usecases.EventUseCase
import com.williamzabot.events.domain.utils.Result
import kotlinx.coroutines.launch

class EventsViewModel @ViewModelInject constructor(private val eventUseCase: EventUseCase) :
    ViewModel() {

    private val _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>> = _events

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private val _apiError = MutableLiveData<Boolean>()
    val apiError: LiveData<Boolean> = _apiError

    fun getEvents() {
        viewModelScope.launch {
            when (val result = eventUseCase.execute()) {
                is Result.Success -> _events.postValue(result.data)
                is Result.Failure -> {
                    when (result.exception) {
                        is BadRequestException -> _apiError.postValue(true)
                        else -> _error.postValue(true)
                    }
                }
            }
        }
    }
}