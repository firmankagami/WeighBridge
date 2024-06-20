package com.firmanda.weighbridge.util

import com.firmanda.weighbridge.data.WeighBrigde
import com.firmanda.weighbridge.model.WeighBridgeModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class UiModelMapper {

    fun uiMapper(id: String, response: WeighBrigde): WeighBridgeModel {
        return WeighBridgeModel(
            id = id,
            dateTime = dateTimeFormatter(response.dateTime),
            timeStamp = response.dateTime,
            driverNameLicense = response.driverName + " - " + response.license,
            driverName = response.driverName,
            license = response.license,
            inboundOutbound = "Inbound: " + response.inbound + " Ton, Outbound:" + response.outbound + " Ton ",
            outbound = response.outbound,
            inbound = response.inbound,
            nettWeigh = "Nett: " + (response.inbound - response.outbound) + " Ton"
        )
    }

    fun uiSort(sortType: String, responses: List<WeighBridgeModel>): List<WeighBridgeModel> {
        val sortedList = when (sortType) {
            SORT_DATE -> {
                responses.sortedByDescending {
                    it.timeStamp
                }
            }
            SORT_NAME -> {
                responses.sortedBy {
                    it.driverName
                }
            }
            else -> {
                responses.sortedBy {
                    it.license
                }
            }
        }

        return sortedList
    }

    fun dateTimeFormatter(timestamp: Long): String {
        val date = Date(timestamp * 1000)
        val formatter = SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.getDefault())
        return formatter.format(date)
    }
}