package com.modbot.bustle.bus_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.modbot.bustle.R
import com.modbot.bustle.util.adapter.BusDetailedRouteItemAdapter
import com.modbot.bustle.util.data.BusDataSource


class BusDetailsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_bus_details, container, false)
        val dataSetPlaces = BusDataSource().loadBusDetailedRoute().route

        val recyclerView = view.findViewById<RecyclerView>(R.id.bus_details_recycler)
        recyclerView.adapter = dataSetPlaces?.let { BusDetailedRouteItemAdapter(it) }

        return view
    }


}