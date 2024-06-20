package com.firmanda.weighbridge.di

import com.firmanda.weighbridge.repository.CreateTicketRepository
import com.firmanda.weighbridge.repository.CreateTicketRepositoryImpl
import com.firmanda.weighbridge.repository.ViewEditTicketRepository
import com.firmanda.weighbridge.repository.ViewEditTicketRepositoryImpl
import com.firmanda.weighbridge.repository.WeighBridgeRepository
import com.firmanda.weighbridge.repository.WeighBridgeRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class WeighBridgeRepositoryModule {

    @Binds
    abstract fun bindWeighBridgeRepository(repo: WeighBridgeRepositoryImpl): WeighBridgeRepository

    @Binds
    abstract fun bindCreateWeighBridgeRepository(repo: CreateTicketRepositoryImpl): CreateTicketRepository

    @Binds
    abstract fun bindViewEditWeighBridgeRepository(repo: ViewEditTicketRepositoryImpl): ViewEditTicketRepository
}