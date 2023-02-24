package com.example.suraapppractice.flows.dashboard.repositories

import com.example.suraapppractice.flows.dashboard.models.SDMovement
import com.example.suraapppractice.service.SDSuraApi
import kotlinx.coroutines.delay

class SDMovementsRepository(val suraApi: SDSuraApi) {
    suspend fun getMovements(
        onResponse: (isOk: Boolean, message: String, movements: List<SDMovement>) -> Unit
    ) {
        /*try {
            val response = suraApi.getMovements()
            onResponse.invoke(response.isSuccessful, response.message(), response.body() ?: emptyList())
        } catch(exception: IOException) {
            onResponse.invoke(false, exception.message.orEmpty(), emptyList())
        }*/
        delay(3000)
        val dummyList = listOf(
            SDMovement(
                1,
                "Jose Perez",
                1676066294000,
                "in",
                200.00,
                "Lo que te debo.",
                "8394829304"
            ),
            SDMovement(
                2,
                "Susana Rodriguez",
                1673560694000,
                "out",
                300.00,
                "Lo de la semana pasada.",
                "8394829305"
            )
        )

        onResponse.invoke(true, "", dummyList)
    }
}