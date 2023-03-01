package com.example.suraapppractice.flows.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.suraapppractice.R
import com.example.suraapppractice.flows.dashboard.fragments.SDDashboardFragment
import com.example.suraapppractice.flows.dashboard.fragments.SDFragmentDashboardListener
import com.example.suraapppractice.flows.dashboard.models.SDMovement
import com.example.suraapppractice.general.extensions.replaceFragment

class SDDashboardActivity: AppCompatActivity(), SDFragmentDashboardListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sd_dashboard_activity)
        supportActionBar?.hide()

        replaceFragment(R.id.fragContainer, SDDashboardFragment())
    }

    override fun onItemClicked(item: SDMovement) {

    }
}