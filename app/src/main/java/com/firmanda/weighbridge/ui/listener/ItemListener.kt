package com.firmanda.weighbridge.ui.listener

import com.firmanda.weighbridge.model.WeighBridgeModel

interface ItemListener {
    fun onClickDialog(ticket: WeighBridgeModel)
    fun onCreateTicket()
    fun onEditTicket(ticket: WeighBridgeModel)
}