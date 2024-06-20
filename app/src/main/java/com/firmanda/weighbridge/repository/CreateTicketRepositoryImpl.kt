package com.firmanda.weighbridge.repository

import androidx.lifecycle.MutableLiveData
import com.firmanda.weighbridge.data.WeighBrigde
import com.firmanda.weighbridge.service.WeighBrigdeService
import com.firmanda.weighbridge.util.Result
import javax.inject.Inject

class CreateTicketRepositoryImpl @Inject constructor(private val service: WeighBrigdeService): CreateTicketRepository {

    override fun createTicket(ticket: WeighBrigde): MutableLiveData<Result<Boolean>> {
        val ticketCreate = MutableLiveData<Result<Boolean>>()
        val ticketId = service.myRef.push().key
        if (ticketId != null) {
            service.myRef.child(ticketId).setValue(ticket).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    ticketCreate.value = Result.Success(true)
                } else {
                    task.exception?.let {
                        ticketCreate.value = Result.Error(it)
                    }
                }
            }
        }
        return ticketCreate
    }
}