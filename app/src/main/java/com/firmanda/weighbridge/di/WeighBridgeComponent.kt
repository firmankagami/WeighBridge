package com.firmanda.weighbridge.di

import com.firmanda.weighbridge.ui.fragment.CreateFragment
import com.firmanda.weighbridge.ui.fragment.MainFragment
import com.firmanda.weighbridge.ui.fragment.ViewEditFragment
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
    fun inject(createFragment: CreateFragment)
    fun inject(viewEditFragment: ViewEditFragment)
}