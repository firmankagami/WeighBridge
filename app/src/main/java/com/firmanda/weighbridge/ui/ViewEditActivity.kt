package com.firmanda.weighbridge.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.firmanda.weighbridge.R
import com.firmanda.weighbridge.di.DaggerWeighBridgeComponent
import com.firmanda.weighbridge.di.WeighBridgeComponent
import com.firmanda.weighbridge.model.WeighBridgeModel
import com.firmanda.weighbridge.ui.fragment.ViewEditFragment
import com.firmanda.weighbridge.util.TICKET_EXTRA

class ViewEditActivity: AppCompatActivity() {

    lateinit var weighBridgeComponent: WeighBridgeComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val extra: WeighBridgeModel = intent.getParcelableExtra(TICKET_EXTRA) ?: WeighBridgeModel()
        setFragment(ViewEditFragment.newInstance(extra))
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    private fun inject(){
        weighBridgeComponent = DaggerWeighBridgeComponent.builder().build()
    }
}