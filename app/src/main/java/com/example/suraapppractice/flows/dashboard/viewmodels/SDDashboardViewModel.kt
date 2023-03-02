package com.example.suraapppractice.flows.dashboard.viewmodels

import android.content.SharedPreferences
import androidx.lifecycle.*
import com.example.suraapppractice.flows.dashboard.actions.SDDashboardActions
import com.example.suraapppractice.flows.dashboard.models.SDMovement
import com.example.suraapppractice.flows.dashboard.repositories.SDMovementsRepository
import com.example.suraapppractice.general.constants.SD_USER_INFO
import com.example.suraapppractice.general.extensions.orZero
import com.example.suraapppractice.general.extensions.toCurrency
import com.example.suraapppractice.general.extensions.toMovCurrency
import kotlinx.coroutines.launch

class SDDashboardViewModel(
    shared: SharedPreferences,
    private val repository: SDMovementsRepository
): ViewModel() {

    //private val repository: SDMovementsRepository by inject(SDMovementsRepository::class.java)

    val nameUser = MutableLiveData<String>()
    val imageUrl = MutableLiveData<String>()
    val balance = MutableLiveData<String>()

    val loading = MutableLiveData<Boolean>()
    var movSelected: SDMovement? = null

    //Detail Movement
    val detailInitialsNameMov = MutableLiveData("")
    val detailAmountMov = MutableLiveData("")
    val detailUserNameMov = MutableLiveData("")
    val detailMessageMov = MutableLiveData("")
    val detailReferenceMov = MutableLiveData("")

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

    fun loadDetailInfo() {
        movSelected?.let {
            val (_, contact, _, type, amount, message, reference) = it
            detailInitialsNameMov.value = contact.take(2)
            detailAmountMov.value = amount.toMovCurrency(
                if (type == "in") '+' else '-'
            )
            detailUserNameMov.value = contact
            detailMessageMov.value = message
            detailReferenceMov.value = reference
        }
    }
}