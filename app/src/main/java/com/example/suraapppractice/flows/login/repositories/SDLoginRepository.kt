package com.example.suraapppractice.flows.login.repositories

import com.example.suraapppractice.flows.login.models.SDRequestLogin
import com.example.suraapppractice.flows.login.models.SDUser
import com.example.suraapppractice.service.SDSuraApi
import kotlinx.coroutines.delay
import java.io.IOException

class SDLoginRepository(val suraApi: SDSuraApi) {
    suspend fun login(
        user: String,
        password: String,
        onResponse: (isOk: Boolean, message: String, userModel: SDUser) -> Unit
    ) {
        try {
            //val response = suraApi.loginUser()
            //onResponse.invoke(response.isSuccessful, response.message(), response.body()?.firstOrNull() ?: SDUser())
            delay(3000)
            onResponse.invoke(true, "", SDUser(1, "Diego Maldonado", "", 30000.0))
        } catch (e: IOException) {
            onResponse.invoke(false, e.message.orEmpty(), SDUser())
        }
    }
}