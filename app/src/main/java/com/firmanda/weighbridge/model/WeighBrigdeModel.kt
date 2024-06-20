package com.firmanda.weighbridge.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeighBridgeModel(
    val id: String = "",
    val dateTime: String = "",
    val timeStamp: Long = 0L,
    val driverNameLicense: String = "",
    val driverName: String = "",
    var license: String = "",
    val inboundOutbound: String = "",
    val inbound: Long = 0L,
    val outbound: Long = 0L,
    val nettWeigh: String = ""
): Parcelable