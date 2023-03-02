package com.example.suraapppractice.flows.dashboard

import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.suraapppractice.R
import com.example.suraapppractice.flows.dashboard.fragments.SDDashboardFragment
import com.example.suraapppractice.flows.dashboard.fragments.SDFragmentDashboardListener
import com.example.suraapppractice.flows.dashboard.fragments.SDMovDetailsFragment
import com.example.suraapppractice.flows.dashboard.models.SDMovement
import com.example.suraapppractice.general.constants.SD_ITEM_MOV_INFO
import com.example.suraapppractice.general.extensions.isCurrentFrag
import com.example.suraapppractice.general.extensions.goBackFragment
import com.example.suraapppractice.general.extensions.replaceFragment

class SDDashboardActivity: AppCompatActivity(), SDFragmentDashboardListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sd_dashboard_activity)
        supportActionBar?.hide()

        replaceFragment(R.id.fragContainer, SDDashboardFragment())

        onBackPressedDispatcher.addCallback(this) {
            if(isCurrentFrag("SDDashboardFragment")) {
                finish()
            } else {
                goBackFragment()
            }
        }
    }

    /*override fun onBackPressed() {
        super.onBackPressed()
    }*/

    override fun onItemClicked() {
        /*val bundle = bundleOf(
            SD_ITEM_MOV_INFO to item
        )*/

        /*val bundle2 = Bundle()
        bundle2.putParcelable(SD_ITEM_MOV_INFO, item)*/

        val fragment = SDMovDetailsFragment()
        //fragment.arguments = bundle

        replaceFragment(R.id.fragContainer, fragment)
    }
}