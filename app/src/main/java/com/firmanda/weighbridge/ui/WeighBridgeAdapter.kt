package com.firmanda.weighbridge.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firmanda.weighbridge.databinding.ItemTicketsBinding
import com.firmanda.weighbridge.model.WeighBrigdeModel

class WeighBridgeAdapter : RecyclerView.Adapter<WeighBridgeViewHolder>() {

    private var listTickets: MutableList<WeighBrigdeModel> = mutableListOf()

    fun updateData(list: List<WeighBrigdeModel>) {
        this.listTickets = list.toMutableList()
        notifyItemRangeChanged(0, listTickets.size)
    }

    override fun getItemCount(): Int {
        return listTickets.size
    }

    override fun onBindViewHolder(holder: WeighBridgeViewHolder, position: Int) {
        holder.bind(listTickets[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeighBridgeViewHolder {
        val binding = ItemTicketsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WeighBridgeViewHolder(binding)
    }
}