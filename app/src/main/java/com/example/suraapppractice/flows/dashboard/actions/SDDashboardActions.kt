package com.example.suraapppractice.flows.dashboard.actions

import com.example.suraapppractice.flows.dashboard.models.SDMovement

sealed class SDDashboardActions {
    data class ShowMovements(val movements: List<SDMovement>): SDDashboardActions()
    data class SDErrorService(val message: String): SDDashboardActions()
}
