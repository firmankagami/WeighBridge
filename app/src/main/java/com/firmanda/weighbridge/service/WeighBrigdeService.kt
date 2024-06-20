package com.firmanda.weighbridge.service

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class WeighBrigdeService {

    private val databaseUrl = "https://weighbridge-a20f0-default-rtdb.asia-southeast1.firebasedatabase.app"
    private val database = Firebase.database(databaseUrl)
    val myRef = database.getReference("weighbrigdes")
    fun setOfflineMode() {
        Firebase.database.setPersistenceEnabled(true)
    }
}