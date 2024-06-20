package com.firmanda.weighbridge.repository

import androidx.lifecycle.MutableLiveData
import com.firmanda.weighbridge.model.WeighBridgeModel
import com.firmanda.weighbridge.util.Result

interface WeighBridgeRepository {
    fun getListWeighBridge(sortType: String): MutableLiveData<Result<List<WeighBridgeModel>>>
}