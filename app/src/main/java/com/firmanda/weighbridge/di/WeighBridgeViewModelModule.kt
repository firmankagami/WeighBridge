package com.firmanda.weighbridge.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.firmanda.weighbridge.util.ViewModelFactory
import com.firmanda.weighbridge.util.ViewModelKey
import com.firmanda.weighbridge.viewmodel.WeighBridgesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class WeighBridgeViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(
        viewModelFactory: ViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(WeighBridgesViewModel::class)
    internal abstract fun bindViewModel(viewModel: WeighBridgesViewModel): ViewModel
}