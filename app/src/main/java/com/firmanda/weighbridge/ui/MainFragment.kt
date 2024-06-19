package com.firmanda.weighbridge.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.firmanda.weighbridge.data.WeighBrigde
import com.firmanda.weighbridge.databinding.FragmentMainBinding
import com.firmanda.weighbridge.model.WeighBrigdeModel
import com.firmanda.weighbridge.viewmodel.WeighBridgesViewModel
import com.firmanda.weighbridge.util.Result
import javax.inject.Inject

class MainFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: WeighBridgesViewModel

    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        initInjector()
        super.onCreate(savedInstanceState)
        val viewModelProvider = ViewModelProvider(this, viewModelFactory)
        viewModel = viewModelProvider[WeighBridgesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadTickets()
    }

    private fun loadTickets() {
        stateLoading()
        viewModel.getListTickets().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Success -> {
                   stateSuccess(result.data)
                }

                is Result.Error -> {
                   stateError()
                }
            }
        }
    }

    private fun renderRecycleView(
        list: List<WeighBrigdeModel>
    ) {
        val weighBridgeAdapter = WeighBridgeAdapter()
        binding.rvNews.apply {
            adapter = weighBridgeAdapter
            layoutManager = LinearLayoutManager(context)
            weighBridgeAdapter.updateData(list)
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showErrorMessage() {
        binding.tvErrorMessage.visibility = View.VISIBLE
        reload()
    }

    private fun reload() {
        binding.tvErrorMessage.setOnClickListener{
            loadTickets()
        }
    }

    private fun hideErrorMessage() {
        binding.tvErrorMessage.visibility = View.GONE
    }

    private fun hideListNews() {
        binding.rvNews.visibility = View.GONE
    }

    private fun showListNews(list: List<WeighBrigdeModel>) {
        renderRecycleView(list)
        binding.rvNews.visibility = View.VISIBLE
    }

    private fun stateLoading() {
        showLoading()
        hideListNews()
        hideErrorMessage()
    }

    private fun stateError() {
        hideLoading()
        hideListNews()
        showErrorMessage()
    }

    private fun stateSuccess(list: List<WeighBrigdeModel>) {
        hideLoading()
        showListNews(list)
        hideErrorMessage()
    }

    private fun initInjector() {
        (activity as MainActivity).weighBridgeComponent.inject(this)
    }

}