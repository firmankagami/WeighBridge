package com.firmanda.weighbridge.util

import com.firmanda.weighbridge.data.WeighBrigde
import com.firmanda.weighbridge.model.WeighBridgeModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class UiModelMapper {

    fun uiMapper(responses: List<WeighBrigde>?): List<WeighBridgeModel> {
        return responses?.map {
            WeighBridgeModel(
                dateTime = dateTimeFormatter(it.dateTime),
                driverNameLicense = it.driverName +" - "+it.license,
                inboundOutbound = "Inbound: "+ it.inbound+ " Ton, Outbound:"+it.outbound+" Ton ",
                nettWeigh = "Nett: "+(it.inbound  - it.outbound) +" Ton"
            )
        } ?: mutableListOf()
    }

    fun dateTimeFormatter(timestamp: Long): String {
        val date = Date(timestamp * 1000)
        val formatter = SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.getDefault())
        return formatter.format(date)
    }
}