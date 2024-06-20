package com.firmanda.weighbridge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.firmanda.weighbridge.model.WeighBridgeModel
import com.firmanda.weighbridge.repository.WeighBridgeRepository
import com.firmanda.weighbridge.util.Result
import javax.inject.Inject

class WeighBridgesViewModel @Inject constructor(
    private val weighBridgeRepository: WeighBridgeRepository
): ViewModel() {

    fun getListTickets(sortType: String): LiveData<Result<List<WeighBridgeModel>>> {
        return weighBridgeRepository.getListWeighBridge(sortType)
    }
}