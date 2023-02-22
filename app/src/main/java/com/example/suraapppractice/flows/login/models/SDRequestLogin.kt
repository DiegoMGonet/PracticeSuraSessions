package com.example.suraapppractice.flows.login.models

import com.google.gson.annotations.SerializedName

data class SDRequestLogin(
    @SerializedName("user") val user: String,
    @SerializedName("password") val password: String
)
