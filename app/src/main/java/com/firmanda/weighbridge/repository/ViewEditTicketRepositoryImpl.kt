package com.firmanda.weighbridge.repository

import androidx.lifecycle.MutableLiveData
import com.firmanda.weighbridge.data.WeighBrigde
import com.firmanda.weighbridge.service.WeighBrigdeService
import com.firmanda.weighbridge.util.Result
import javax.inject.Inject

class ViewEditTicketRepositoryImpl @Inject constructor(private val service: WeighBrigdeService):
    ViewEditTicketRepository {

    override fun editTicket(id: String, ticket: WeighBrigde): MutableLiveData<Result<Boolean>> {
        val ticketEdit = MutableLiveData<Result<Boolean>>()
        service.myRef.child(id).setValue(ticket).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                ticketEdit.value = Result.Success(true)
            } else {
                task.exception?.let {
                    ticketEdit.value = Result.Error(it)
                }
            }
        }
        return ticketEdit
    }
}