package com.example.suraapppractice.flows.login.repositories

import com.example.suraapppractice.flows.login.models.SDRequestLogin
import com.example.suraapppractice.flows.login.models.SDUser
import com.example.suraapppractice.service.SDSuraApi
import java.io.IOException

class SDLoginRepository(val suraApi: SDSuraApi) {
    suspend fun login(
        user: String,
        password: String,
        onResponse: (isOk: Boolean, message: String, userModel: SDUser) -> Unit
    ) {
        try {
            val response = suraApi.loginUser()

            onResponse.invoke(response.isSuccessful, response.message(), response.body()?.firstOrNull() ?: SDUser())
        } catch (e: IOException) {
            onResponse.invoke(false, e.message.orEmpty(), SDUser())
        }
    }
}