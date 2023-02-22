package com.example.suraapppractice.service

import com.example.suraapppractice.flows.login.models.SDRequestLogin
import com.example.suraapppractice.flows.login.models.SDUser
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SDSuraApi {

    @POST("users")
    suspend fun loginUser(@Body userInfo: SDRequestLogin): Response<SDUser>
}