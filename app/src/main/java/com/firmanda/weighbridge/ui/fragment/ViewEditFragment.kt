package com.firmanda.weighbridge.ui.fragment

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.firmanda.weighbridge.data.WeighBrigde
import com.firmanda.weighbridge.databinding.FragmentVieweditBinding
import com.firmanda.weighbridge.model.WeighBridgeModel
import com.firmanda.weighbridge.ui.ViewEditActivity
import com.firmanda.weighbridge.util.Result
import com.firmanda.weighbridge.util.TICKET_EXTRA
import com.firmanda.weighbridge.viewmodel.ViewEditViewModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class ViewEditFragment: Fragment(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener{

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ViewEditViewModel

    private lateinit var binding: FragmentVieweditBinding
    private var weighBridgeModel = WeighBridgeModel()

    private var id: String = ""
    private var name: String = ""
    private var license: String = ""
    private var inbound: Long = 0L
    private var outbound: Long = 0L
    private var timeStamp: Long = 0L

    private var day = 0
    private var month: Int = 0
    private var year: Int = 0
    private var hour: Int = 0
    private var minute: Int = 0

    private var myDay = 0
    private var myMonth: Int = 0
    private var myYear: Int = 0
    private var myHour: Int = 0
    private var myMinute: Int = 0

    private var datePickerDialog: DatePickerDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        initInjector()
        super.onCreate(savedInstanceState)
        val viewModelProvider = ViewModelProvider(this, viewModelFactory)
        viewModel = viewModelProvider[ViewEditViewModel::class.java]

        weighBridgeModel = arguments?.getParcelable(TICKET_EXTRA) ?: WeighBridgeModel()

        id = weighBridgeModel.id
        name = weighBridgeModel.driverName
        license = weighBridgeModel.license
        inbound = weighBridgeModel.inbound
        outbound = weighBridgeModel.outbound
        timeStamp = weighBridgeModel.timeStamp

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVieweditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uiProcess()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun uiProcess() {
        binding.etName.setText(weighBridgeModel.driverName)
        binding.etTime.setText(weighBridgeModel.dateTime)
        binding.etInbound.setText(weighBridgeModel.inbound.toString())
        binding.etOutbound.setText(weighBridgeModel.outbound.toString())
        binding.etLicense.setText(weighBridgeModel.license)

        binding.etTime.setOnTouchListener { _, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    shownDate()
                }
            }
            false
        }

        binding.btnEdit.setOnClickListener {
            editTicket()
        }
    }

    private fun editTicket() {
        name = binding.etName.text.toString()
        license = binding.etLicense.text.toString()
        inbound = binding.etInbound.text.toString().toLong()
        outbound = binding.etOutbound.text.toString().toLong()

        if (name.isEmpty() || license.isEmpty() || inbound <= 0 || outbound <= 0 || timeStamp <= 0) {
            Toast.makeText(context, "Fill the empty field(s)", Toast.LENGTH_LONG).show()
        } else {
            val ticket = WeighBrigde(
                dateTime = timeStamp,
                driverName = name,
                license = license,
                inbound = inbound,
                outbound = outbound
            )
            processEditTicket(ticket)
        }
    }

    private fun processEditTicket(ticket: WeighBrigde) {
        viewModel.editTicket(id, ticket).observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Success -> {
                    activity?.finish()
                }

                is Result.Error -> {
                    Toast.makeText(context, "Error "+result.error.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun shownDate() {
        context?.let { context ->
            val calendar: Calendar = Calendar.getInstance()
            day = calendar.get(Calendar.DAY_OF_MONTH)
            month = calendar.get(Calendar.MONTH)
            year = calendar.get(Calendar.YEAR)
            datePickerDialog =
                DatePickerDialog(context, this@ViewEditFragment, year, month, day)
            datePickerDialog?.show()
        }
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        context?.let { context ->
            myDay = dayOfMonth
            myMonth = month
            myYear = year
            val calendar: Calendar = Calendar.getInstance()
            hour = calendar.get(Calendar.HOUR)
            minute = calendar.get(Calendar.MINUTE)
            datePickerDialog?.dismiss()
            val timePickerDialog = TimePickerDialog(
                context, this, hour, minute,
                DateFormat.is24HourFormat(context)
            )
            timePickerDialog.show()
        }
    }

    override fun onTimeSet(timePickerDialog: TimePicker, hourOfDay: Int, minute: Int) {
        myHour = hourOfDay
        myMinute = minute
        datePickerDialog?.dismiss()
        val localData =  LocalDateTime.of(myYear, myMonth, myDay, myHour, myMinute, 0)
        timeStamp = localData.toEpochSecond(ZoneOffset.UTC)

        binding.etTime.setText(getFormattedDateTime(timeStamp))
    }

    private fun getFormattedDateTime(timestamp: Long): String {
        val date = Date(timestamp * 1000)
        val formatter = SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.getDefault())
        return formatter.format(date)
    }

    private fun initInjector() {
        (activity as ViewEditActivity).weighBridgeComponent.inject(this)
    }

    companion object {
        fun newInstance(extra: WeighBridgeModel): ViewEditFragment {
            val fragment = ViewEditFragment()
            val args = Bundle()
            args.putParcelable(TICKET_EXTRA, extra)
            fragment.arguments = args
            return fragment
        }
    }
}