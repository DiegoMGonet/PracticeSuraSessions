package com.example.suraapppractice.flows.dashboard.viewmodels

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.suraapppractice.flows.dashboard.actions.SDDashboardActions
import com.example.suraapppractice.flows.dashboard.repositories.SDMovementsRepository
import com.example.suraapppractice.general.constants.SD_SHARED_PREF
import com.example.suraapppractice.general.constants.SD_USER_INFO
import com.example.suraapppractice.general.extensions.orZero
import com.example.suraapppractice.general.extensions.toCurrency
import com.example.suraapppractice.service.SDRetrofitSingle
import com.example.suraapppractice.service.SDSuraApi
import kotlinx.coroutines.launch

class SDDashboardViewModel(
    private val shared: SharedPreferences
): ViewModel() {
    companion object {
        val Factory: ViewModelProvider.Factory = object: ViewModelProvider.Factory {
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
                val shared = application.getSharedPreferences(SD_SHARED_PREF, Context.MODE_PRIVATE)
                return SDDashboardViewModel(shared) as T
            }
        }
    } // static ViewModelProvider.Factory Factory;

    private val suraApi = SDRetrofitSingle.getInstance().create(SDSuraApi::class.java)
    private val repository = SDMovementsRepository(suraApi)

    val nameUser = MutableLiveData<String>()
    val imageUrl = MutableLiveData<String>()
    val balance = MutableLiveData<String>()

    val loading = MutableLiveData<Boolean>()

    private val _action = MutableLiveData<SDDashboardActions>()
    val action: LiveData<SDDashboardActions> = _action

    init {
        val userData = shared.getString(SD_USER_INFO, "").orEmpty().split("|")

        if (userData.size == 4) {
            val balanceFormatted = userData[3].toDoubleOrNull().orZero.toCurrency()
            nameUser.value = userData[1]
            imageUrl.value = userData[2]
            balance.value = balanceFormatted
        }
    }

    fun loadMovements() {
        loading.value = true
        viewModelScope.launch {
            repository.getMovements { isOk, message, movements ->
                _action.value = if (isOk) {
                    SDDashboardActions.ShowMovements(movements)
                } else {
                    SDDashboardActions.SDErrorService(message)
                }

                loading.value = false
            }
        }
    }
}