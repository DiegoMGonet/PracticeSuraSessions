package com.example.suraapppractice.flows.login.models

data class SDUser(
    val idUser: Int = 0,
    val name: String = "",
    val urlImage: String = "",
    val balance: Double = 0.0
) {
    override fun toString() =
        listOf(idUser, name, urlImage, balance).joinToString("|")
}