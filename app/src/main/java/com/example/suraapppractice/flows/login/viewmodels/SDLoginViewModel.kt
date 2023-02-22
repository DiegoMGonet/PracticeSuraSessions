package com.example.suraapppractice.flows.login.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.suraapppractice.flows.login.actions.SDFieldType
import com.example.suraapppractice.flows.login.actions.SDLoginActions
import com.example.suraapppractice.flows.login.extensions.isValidPassword
import com.example.suraapppractice.flows.login.extensions.isValidUser
import com.example.suraapppractice.flows.login.repositories.SDLoginRepository
import com.example.suraapppractice.service.SDRetrofitSingle
import com.example.suraapppractice.service.SDSuraApi
import kotlinx.coroutines.launch

class SDLoginViewModel: ViewModel() {
    private val _action = MutableLiveData<SDLoginActions>()
    val action: LiveData<SDLoginActions> = _action

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val suraApi = SDRetrofitSingle.getInstance().create(SDSuraApi::class.java)
    private val repository = SDLoginRepository(suraApi)

    fun login(user: String, password: String) {
        if(!user.isValidUser()) {
            _action.value = SDLoginActions.SDErrorDataValidation(SDFieldType.SD_USER_FIELD)
            return
        }
        if(!password.isValidPassword()) {
            _action.value = SDLoginActions.SDErrorDataValidation(SDFieldType.SD_PASSWORD_FIELD)
            return
        }

        _loading.value = true
        viewModelScope.launch {
            repository.login(user, password) { isOk, message, userModel ->
                _action.value = if (isOk) {
                    SDLoginActions.SDLoginSuccess
                } else {
                    SDLoginActions.SDErrorService(message)
                }

                println("El usuario: $userModel")
            }
            _loading.value = false
        }
    }
}