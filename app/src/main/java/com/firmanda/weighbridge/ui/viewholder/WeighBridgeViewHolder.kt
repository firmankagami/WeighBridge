package com.firmanda.weighbridge.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.firmanda.weighbridge.databinding.ItemTicketsBinding
import com.firmanda.weighbridge.model.WeighBridgeModel
import com.firmanda.weighbridge.ui.listener.ItemListener

class WeighBridgeViewHolder (
    private val binding: ItemTicketsBinding,
    private val listener: ItemListener
): RecyclerView.ViewHolder(binding.root) {

    fun bind(model: WeighBridgeModel) {
        with(binding) {
            tvDate.text = model.dateTime
            tvNett.text= model.nettWeigh
            tvDriverNamePlate.text= model.driverNameLicense
            tvInboundOutbound.text = model.inboundOutbound

            root.setOnClickListener {
                listener.onClickDialog(model)
            }

            buttonCreate.setOnClickListener {
                listener.onCreateTicket()
            }

            buttonView.setOnClickListener {
                listener.onEditTicket(model)
            }
        }
    }
}