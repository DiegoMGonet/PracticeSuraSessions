package com.example.suraapppractice

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.suraapppractice.flows.dashboard.SDDashboardActivity
import com.example.suraapppractice.flows.login.SDLoginActivity
import com.example.suraapppractice.general.constants.SD_IS_APP_ACTIVE
import com.example.suraapppractice.general.constants.SD_SHARED_PREF

class SDSplashActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val ownSplash = installSplashScreen()
        super.onCreate(savedInstanceState)

        ownSplash.setKeepOnScreenCondition { true }

        val sharedPref = getSharedPreferences(SD_SHARED_PREF, Context.MODE_PRIVATE)
        val isActive = sharedPref.getBoolean(SD_IS_APP_ACTIVE, false)

        val intent = if (isActive) {
            Intent(this, SDDashboardActivity::class.java)
        } else {
            Intent(this, SDLoginActivity::class.java)
        }

        startActivity(intent)
        finish()
    }
}