package com.example.suraapppractice.flows.login.actions

sealed class SDLoginActions {
    data class SDErrorDataValidation(val field: SDFieldType): SDLoginActions()
    data class SDErrorService(val message: String): SDLoginActions()
    object SDLoginSuccess: SDLoginActions()
}

enum class SDFieldType {
    SD_USER_FIELD,
    SD_PASSWORD_FIELD
}
