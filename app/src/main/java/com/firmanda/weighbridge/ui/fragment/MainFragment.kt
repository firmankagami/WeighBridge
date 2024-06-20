package com.firmanda.weighbridge.ui.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.firmanda.weighbridge.R
import com.firmanda.weighbridge.databinding.FragmentMainBinding
import com.firmanda.weighbridge.model.WeighBridgeModel
import com.firmanda.weighbridge.ui.CreateActivity
import com.firmanda.weighbridge.ui.MainActivity
import com.firmanda.weighbridge.ui.ViewEditActivity
import com.firmanda.weighbridge.ui.adapter.WeighBridgeAdapter
import com.firmanda.weighbridge.ui.listener.ItemListener
import com.firmanda.weighbridge.util.NUMBER_EXTRA
import com.firmanda.weighbridge.util.RESULT_OK
import com.firmanda.weighbridge.util.Result
import com.firmanda.weighbridge.util.TICKET_EXTRA
import com.firmanda.weighbridge.viewmodel.WeighBridgesViewModel
import javax.inject.Inject


class MainFragment : Fragment(), ItemListener {

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

    override fun onCreateTicket() {
        val intent = Intent(context, CreateActivity::class.java)
        startActivityForResult(intent, NUMBER_EXTRA)
    }

    override fun onClickDialog(ticket: WeighBridgeModel) {
        showDialog(ticket)
    }

    override fun onEditTicket(ticket: WeighBridgeModel) {
        val intent = Intent(context, ViewEditActivity::class.java)
        intent.putExtra(TICKET_EXTRA, ticket)
        startActivityForResult(intent, NUMBER_EXTRA)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == NUMBER_EXTRA  && resultCode  == RESULT_OK) {
            loadTickets()
        }
    }

    private fun showDialog(ticket: WeighBridgeModel) {
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_ticket, null)

        val tvNett: TextView = dialogView.findViewById(R.id.tv_nett)
        val tvDate: TextView = dialogView.findViewById(R.id.tv_date)
        val tvDriverNamePlate: TextView = dialogView.findViewById(R.id.tv_driver_name_plate)
        val tvInboundOutbound: TextView = dialogView.findViewById(R.id.tv_inbound_outbound)

        tvNett.text = ticket.nettWeigh
        tvDate.text = ticket.dateTime
        tvDriverNamePlate.text = ticket.driverNameLicense
        tvInboundOutbound.text = ticket.inboundOutbound

        val builder = AlertDialog.Builder(context).apply {
            setView(dialogView)
            setTitle("Detail Ticket")
            setPositiveButton("OK") { dialog, id ->
            }
        }

        val dialog = builder.create()
        dialog.show()
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
        list: List<WeighBridgeModel>
    ) {
        val weighBridgeAdapter = WeighBridgeAdapter(this)
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

    private fun showListNews(list: List<WeighBridgeModel>) {
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

    private fun stateSuccess(list: List<WeighBridgeModel>) {
        hideLoading()
        showListNews(list)
        hideErrorMessage()
    }

    private fun initInjector() {
        (activity as MainActivity).weighBridgeComponent.inject(this)
    }

}