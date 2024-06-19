package com.firmanda.weighbridge.di

import com.firmanda.weighbridge.ui.MainFragment
import dagger.Component

@Component(
    modules = [
        WeighBridgeNetworkModule::class,
        WeighBridgeViewModelModule::class,
        WeighBridgeRepositoryModule::class
    ]
)
interface WeighBridgeComponent {
    fun inject(mainFragment: MainFragment)
}