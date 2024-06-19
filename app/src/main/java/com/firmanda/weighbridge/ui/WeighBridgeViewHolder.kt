package com.firmanda.weighbridge.ui

import androidx.recyclerview.widget.RecyclerView
import com.firmanda.weighbridge.databinding.ItemTicketsBinding
import com.firmanda.weighbridge.model.WeighBrigdeModel

class WeighBridgeViewHolder (
    private val binding: ItemTicketsBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(model: WeighBrigdeModel) {
        with(binding) {
            tvDate.text = model.dateTime
            tvNett.text= model.nettWeigh.toString()
            tvDriverNamePlate.text= model.driverNameLicense
            tvInboundOutbound.text = model.inboundOutbound
        }
    }
}