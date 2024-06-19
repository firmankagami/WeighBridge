package com.firmanda.weighbridge.di

import com.firmanda.weighbridge.service.WeighBrigdeService
import com.firmanda.weighbridge.util.UiModelMapper
import dagger.Module
import dagger.Provides

@Module
class WeighBridgeNetworkModule {

    @Provides
    fun provideService(): WeighBrigdeService {
        return WeighBrigdeService()
    }

    @Provides
    fun provideUiModelMapper(): UiModelMapper {
        return UiModelMapper()
    }
}