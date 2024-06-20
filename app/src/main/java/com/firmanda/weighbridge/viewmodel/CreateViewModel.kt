package com.firmanda.weighbridge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.firmanda.weighbridge.data.WeighBrigde
import com.firmanda.weighbridge.repository.CreateTicketRepository
import com.firmanda.weighbridge.util.Result
import javax.inject.Inject

class CreateViewModel @Inject constructor(private val repository: CreateTicketRepository): ViewModel() {

    fun createTicket(ticket: WeighBrigde): LiveData<Result<Boolean>> {
        return repository.createTicket(ticket)
    }
}