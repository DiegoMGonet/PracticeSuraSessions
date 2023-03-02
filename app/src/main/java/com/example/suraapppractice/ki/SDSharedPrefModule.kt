package com.example.suraapppractice.ki

import android.content.Context
import android.content.SharedPreferences
import com.example.suraapppractice.general.constants.SD_SHARED_PREF
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val sdSharedPref = module {
    single {
        provideSharedPref(androidContext())
    }
}

fun provideSharedPref(context: Context): SharedPreferences =
    context.getSharedPreferences(SD_SHARED_PREF, Context.MODE_PRIVATE)
