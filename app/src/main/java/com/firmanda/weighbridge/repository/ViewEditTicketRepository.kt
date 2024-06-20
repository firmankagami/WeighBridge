package com.firmanda.weighbridge.repository

import androidx.lifecycle.MutableLiveData
import com.firmanda.weighbridge.data.WeighBrigde
import com.firmanda.weighbridge.util.Result

interface ViewEditTicketRepository {
    fun editTicket(id: String, ticket: WeighBrigde): MutableLiveData<Result<Boolean>>
}