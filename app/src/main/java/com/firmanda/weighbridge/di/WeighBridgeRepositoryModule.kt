package com.firmanda.weighbridge.di

import com.firmanda.weighbridge.repository.WeighBridgeRepository
import com.firmanda.weighbridge.repository.WeighBridgeRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class WeighBridgeRepositoryModule {

    @Binds
    abstract fun bindWeighBridgeRepository(repo: WeighBridgeRepositoryImpl): WeighBridgeRepository
}