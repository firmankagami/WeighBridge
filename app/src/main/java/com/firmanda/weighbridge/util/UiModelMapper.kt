package com.firmanda.weighbridge.util

import com.firmanda.weighbridge.data.WeighBrigde
import com.firmanda.weighbridge.model.WeighBrigdeModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class UiModelMapper {

    fun uiMapper(responses: List<WeighBrigde>?): List<WeighBrigdeModel> {
        return responses?.map {
            WeighBrigdeModel(
                dateTime = dateTimeFormatter(it.dateTime),
                driverNameLicense = it.driverName +" - "+it.license,
                inboundOutbound = "Inbound: "+ it.inbound+ " Outbound:"+it.outbound+" ",
                nettWeigh = "Nett: "+(it.inbound  - it.outbound)
            )
        } ?: mutableListOf()
    }

    fun dateTimeFormatter(timestamp: Long): String {
        val date = Date(timestamp * 1000)
        val formatter = SimpleDateFormat("dd MMMM yyyy HH:mm:ss", Locale.getDefault())
        return formatter.format(date)
    }
}