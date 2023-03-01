package com.example.suraapppractice.flows.dashboard.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.suraapppractice.R
import com.example.suraapppractice.databinding.SdDashboardFragmentBinding
import com.example.suraapppractice.flows.dashboard.actions.SDDashboardActions
import com.example.suraapppractice.flows.dashboard.adapters.SDMovementsAdapter
import com.example.suraapppractice.flows.dashboard.models.SDMovement
import com.example.suraapppractice.flows.dashboard.viewmodels.SDDashboardViewModel
import com.example.suraapppractice.general.extensions.show
import com.example.suraapppractice.general.extensions.showMessage
import com.squareup.picasso.Picasso

class SDDashboardFragment: Fragment() {
    private var _binding: SdDashboardFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SDDashboardViewModel by viewModels { SDDashboardViewModel.Factory }

    private var listener: SDFragmentDashboardListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? SDFragmentDashboardListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SdDashboardFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.nameUser.observe(viewLifecycleOwner){ binding.textViewNameUser.text = it }
        viewModel.balance.observe(viewLifecycleOwner) { binding.textViewBalance.text = it }
        viewModel.imageUrl.observe(viewLifecycleOwner){
            Picasso
                .with(requireContext())
                .load(it)
                .into(binding.imageViewUserImage)
        }
        viewModel.action.observe(viewLifecycleOwner, Observer(this::observeActions))
        //viewModel.action.observe(viewLifecycleOwner) { observeActions(it) }
        viewModel.loading.observe(viewLifecycleOwner, Observer(this::showProgressLoading))

        viewModel.loadMovements()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
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
        val adapter = SDMovementsAdapter(movements) {
            listener?.onItemClicked(it)
        }
        binding.recyclerMovements.adapter = adapter
    }

    private fun showProgressLoading(show: Boolean) {
        binding.progressMovements.show(show)
        binding.recyclerMovements.show(!show)
    }
}

interface SDFragmentDashboardListener {
    fun onItemClicked(item: SDMovement)
}