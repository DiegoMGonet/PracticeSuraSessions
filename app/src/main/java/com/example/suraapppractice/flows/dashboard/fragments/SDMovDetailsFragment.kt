package com.example.suraapppractice.flows.dashboard.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.suraapppractice.databinding.SdMovementDetailFragmentBinding
import com.example.suraapppractice.flows.dashboard.models.SDMovement
import com.example.suraapppractice.flows.dashboard.viewmodels.SDDashboardViewModel
import com.example.suraapppractice.general.constants.SD_ITEM_MOV_INFO
import com.example.suraapppractice.general.extensions.toMovCurrency
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class SDMovDetailsFragment: Fragment() {
    private var _binding: SdMovementDetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SDDashboardViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SdMovementDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        viewModel.loadDetailInfo()
        binding.buttonAcept.setOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }

        /*val movement = arguments?.getParcelable<SDMovement>(SD_ITEM_MOV_INFO) //Para api 33

        movement?.let {
            val (_, contact, _, type, amount, message, reference) = it

            binding.apply {
                textViewInitialsDetails.text = contact.take(2)
                textViewAmount.text = amount.toMovCurrency(
                    if (type == "in") '+' else '-'
                )
                textViewName.text = contact
                textViewMessage.text = message
                textViewReference.text = reference
                buttonAcept.setOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }
            }
        }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}