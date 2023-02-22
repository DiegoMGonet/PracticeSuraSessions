package com.example.suraapppractice.flows.login.extensions

import android.util.Patterns
import androidx.core.text.isDigitsOnly

fun String.isValidUser() =
    this.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidPassword() =
    this.length == 10 && this.isDigitsOnly()