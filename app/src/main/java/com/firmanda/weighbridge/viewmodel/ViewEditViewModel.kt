package com.firmanda.weighbridge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.firmanda.weighbridge.data.WeighBrigde
import com.firmanda.weighbridge.repository.ViewEditTicketRepository
import com.firmanda.weighbridge.util.Result
import javax.inject.Inject

class ViewEditViewModel @Inject constructor(private val repository: ViewEditTicketRepository): ViewModel() {

    fun editTicket(id: String, ticket: WeighBrigde): LiveData<Result<Boolean>> {
        return repository.editTicket(id, ticket)
    }
}