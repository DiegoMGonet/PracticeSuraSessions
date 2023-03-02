package com.example.suraapppractice.flows.login.viewmodels

import android.content.SharedPreferences
import androidx.lifecycle.*
import com.example.suraapppractice.flows.login.actions.SDFieldType
import com.example.suraapppractice.flows.login.actions.SDLoginActions
import com.example.suraapppractice.flows.login.extensions.isValidPassword
import com.example.suraapppractice.flows.login.extensions.isValidUser
import com.example.suraapppractice.flows.login.repositories.SDLoginRepository
import com.example.suraapppractice.general.constants.SD_IS_APP_ACTIVE
import com.example.suraapppractice.general.constants.SD_USER_INFO
import kotlinx.coroutines.launch

class SDLoginViewModel(
    private val sharedPref: SharedPreferences,
    private val repository: SDLoginRepository //Se puede inyectar la dependencia por el constructor
): ViewModel() {

    private val _action = MutableLiveData<SDLoginActions>()
    val action: LiveData<SDLoginActions> = _action

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    //Se puede inyectar variables
    //private val repository: SDLoginRepository by inject(SDLoginRepository::class.java)

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
                    sharedPref.edit().apply {
                        putString(SD_USER_INFO, userModel.toString())
                        putBoolean(SD_IS_APP_ACTIVE, true)
                        apply()
                    }
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