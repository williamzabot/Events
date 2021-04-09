package com.williamzabot.events.presenter.features.checkin

import android.text.Editable
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.williamzabot.events.data.exception.BadRequestException
import com.williamzabot.events.domain.model.CheckinBody
import com.williamzabot.events.domain.usecases.CheckinUseCase
import com.williamzabot.events.domain.utils.Result
import com.williamzabot.events.presenter.extensions.emailIsValid
import kotlinx.coroutines.launch

class CheckinViewModel @ViewModelInject constructor(private val checkinUseCase: CheckinUseCase) :
    ViewModel() {

    private val _nameFieldState = MutableLiveData<FieldState>()
    val nameFieldState: LiveData<FieldState> = _nameFieldState

    private val _emailFieldState = MutableLiveData<FieldState>()
    val emailFieldState: LiveData<FieldState> = _emailFieldState

    private val _invalidEmail = MutableLiveData<Boolean>()
    val invalidEmail: LiveData<Boolean> = _invalidEmail

    private val _checkinSuccess = MutableLiveData<Boolean>()
    val checkinSuccess: LiveData<Boolean> = _checkinSuccess

    private val _checkinFailed = MutableLiveData<Boolean>()
    val checkinFailed: LiveData<Boolean> = _checkinFailed

    private val _errorAPI = MutableLiveData<Boolean>()
    val errorAPI: LiveData<Boolean> = _errorAPI

    fun sendNameAndEmailForCheckin(id: String, name: Editable?, email: Editable?) {
        if (fieldOk(name)) {
            _nameFieldState.postValue(FieldState.FieldOk)
        } else {
            _nameFieldState.postValue(FieldState.FieldError)
        }

        if (fieldOk(email)) {
            if (emailOk(email)) {
                _emailFieldState.postValue(FieldState.FieldOk)
            } else {
                _invalidEmail.postValue(true)
            }

        } else {
            _emailFieldState.postValue(FieldState.FieldError)
        }

        if (fieldOk(name) && fieldOk(email) && emailOk(email)) {
            checkin(id, name.toString(), email.toString())
        }
    }

    private fun fieldOk(editable: Editable?): Boolean {
        return !editable.isNullOrBlank()
    }

    private fun emailOk(editable: Editable?): Boolean {
        return editable.toString().emailIsValid()
    }

    fun checkin(id: String, name: String, email: String) {
        val checkinBody = CheckinBody(id, name, email)
        viewModelScope.launch {
            when (val result = checkinUseCase.execute(CheckinUseCase.Params(checkinBody))) {
                is Result.Success -> _checkinSuccess.postValue(true)
                is Result.Failure -> {
                    when(result.exception){
                        is BadRequestException -> _errorAPI.postValue(true)
                        else -> _checkinFailed.postValue(true)
                    }
                }
            }

        }
    }

    sealed class FieldState {
        object FieldError : FieldState()
        object FieldOk : FieldState()
    }

}