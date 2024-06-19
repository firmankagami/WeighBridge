package com.firmanda.weighbridge.data

data class WeighBrigde(
    val dateTime: Long = 0L,
    val driverName: String = "",
    val inbound: Long = 0L,
    val license: String = "",
    val outbound: Long = 0L
)