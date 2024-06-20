package com.firmanda.weighbridge.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.firmanda.weighbridge.databinding.FragmentVieweditBinding
import com.firmanda.weighbridge.ui.ViewEditActivity
import com.firmanda.weighbridge.viewmodel.ViewEditViewModel
import javax.inject.Inject

class ViewEditFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ViewEditViewModel

    private lateinit var binding: FragmentVieweditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        initInjector()
        super.onCreate(savedInstanceState)
        val viewModelProvider = ViewModelProvider(this, viewModelFactory)
        viewModel = viewModelProvider[ViewEditViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVieweditBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initInjector() {
        (activity as ViewEditActivity).weighBridgeComponent.inject(this)
    }
}