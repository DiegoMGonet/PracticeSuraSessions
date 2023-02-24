package com.example.suraapppractice.flows.dashboard.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.suraapppractice.R
import com.example.suraapppractice.databinding.SdDashboardFragmentBinding
import com.example.suraapppractice.flows.dashboard.actions.SDDashboardActions
import com.example.suraapppractice.flows.dashboard.models.SDMovement
import com.example.suraapppractice.flows.dashboard.viewmodels.SDDashboardViewModel
import com.example.suraapppractice.general.extensions.showMessage

class SDDashboardFragment: Fragment() {
    private var _binding: SdDashboardFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SdDashboardFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun observeActions(action: SDDashboardActions) {
        when(action) {
            is SDDashboardActions.SDErrorService -> { showMessage(action.message) }
            is SDDashboardActions.ShowMovements -> { populateMovements(action.movements) }
        }
    }

    private fun populateMovements(movements: List<SDMovement>) {

    }
}