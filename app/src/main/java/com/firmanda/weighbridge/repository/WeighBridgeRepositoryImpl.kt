package com.firmanda.weighbridge.repository

import androidx.lifecycle.MutableLiveData
import com.firmanda.weighbridge.data.WeighBrigde
import com.firmanda.weighbridge.model.WeighBridgeModel
import com.firmanda.weighbridge.service.WeighBrigdeService
import com.firmanda.weighbridge.util.Result
import com.firmanda.weighbridge.util.UiModelMapper
import javax.inject.Inject

class WeighBridgeRepositoryImpl @Inject constructor(
    private val service: WeighBrigdeService,
    private val uiModelMapper: UiModelMapper
) : WeighBridgeRepository {

    override fun getListWeighBridge(): MutableLiveData<Result<List<WeighBridgeModel>>> {
        val ticketListLiveData = MutableLiveData<Result<List<WeighBridgeModel>>>()
        service.myRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val result = task.result
                result?.let {
                    val ticketList = mutableListOf<WeighBridgeModel>()
                    for (ticketsSnapshot in it.children) {
                        val ticket = ticketsSnapshot.getValue(WeighBrigde::class.java)
                        val id = ticketsSnapshot.key ?: ""
                        ticket?.let { ticketList.add(uiModelMapper.uiMapper(id, it)) }
                    }
                    ticketListLiveData.value = Result.Success(ticketList)
                }
            } else {
                task.exception?.let {
                    ticketListLiveData.value = Result.Error(it)
                }
            }
        }
        return ticketListLiveData
    }
}