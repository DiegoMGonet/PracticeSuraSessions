package com.example.suraapppractice.flows.dashboard.models

data class SDMovement(
    val id: Int,
    val contact: String,
    val date: Long,
    val typeMovement: String,
    val amount: Double,
    val message: String,
    val reference: String
)
