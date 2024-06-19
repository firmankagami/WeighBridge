package com.firmanda.weighbridge.repository

import androidx.lifecycle.MutableLiveData
import com.firmanda.weighbridge.data.WeighBrigde
import com.firmanda.weighbridge.model.WeighBrigdeModel
import com.firmanda.weighbridge.util.Result

interface WeighBridgeRepository {
    fun getListWeighBridge(): MutableLiveData<Result<List<WeighBrigdeModel>>>
}