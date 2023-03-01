package com.example.suraapppractice.flows.dashboard.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.suraapppractice.databinding.SdItemMovementLayoutBinding
import com.example.suraapppractice.flows.dashboard.models.SDMovement
import com.example.suraapppractice.general.extensions.getResourcesFromMov
import com.example.suraapppractice.general.extensions.toCurrency
import com.example.suraapppractice.general.extensions.toFormatDate
import com.example.suraapppractice.general.extensions.toMovCurrency

class SDMovementsAdapter(
    private val movements: List<SDMovement>,
    private val onClickItem: (item: SDMovement) -> Unit
): RecyclerView.Adapter<SDMovementsAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: SdItemMovementLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SdItemMovementLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (_, contact, date, type, amount, _, _) = movements[position]

        holder.binding.apply {
            textViewNameContact.text = contact
            textViewInitialsName.text = contact.take(2)
            textViewAmountContact.text = amount.toMovCurrency(
                if (type == "in") '+' else '-'
            )
            textViewDate.text = date.toFormatDate()

            val movResources = getResourcesFromMov(type)
            imageViewType.setImageResource(movResources.first)
            imageViewType.setColorFilter(
                ContextCompat.getColor(root.context, movResources.second)
            )

            cardViewMovement.setOnClickListener { onClickItem.invoke(movements[position]) }
        }
    }

    override fun getItemCount() = movements.size
}