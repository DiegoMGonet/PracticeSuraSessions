package com.example.suraapppractice.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SDRetrofitSingle {
    private val baseUrl = "https://63d47d900e7ae91a009df5ab.mockapi.io/api/v1/"

    fun getInstance(): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}