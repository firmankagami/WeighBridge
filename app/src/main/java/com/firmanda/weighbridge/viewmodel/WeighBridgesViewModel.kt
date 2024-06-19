package com.firmanda.weighbridge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.firmanda.weighbridge.data.WeighBrigde
import com.firmanda.weighbridge.model.WeighBrigdeModel
import com.firmanda.weighbridge.repository.WeighBridgeRepository
import com.firmanda.weighbridge.util.Result
import javax.inject.Inject

class WeighBridgesViewModel @Inject constructor(
    private val weighBridgeRepository: WeighBridgeRepository
): ViewModel() {

    fun getListTickets(): LiveData<Result<List<WeighBrigdeModel>>> {
        return weighBridgeRepository.getListWeighBridge()
    }
}