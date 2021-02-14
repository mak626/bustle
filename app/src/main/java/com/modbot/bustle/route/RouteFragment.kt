package com.modbot.bustle.route

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.modbot.bustle.R
import com.modbot.bustle.util.adapter.RouteItemAdapter
import com.modbot.bustle.util.data.BusDataSource


class RouteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_route, container, false)
        val dataSetBus = BusDataSource().loadRoute()

        val recyclerView = view.findViewById<RecyclerView>(R.id.route_recycler)
        recyclerView.adapter = RouteItemAdapter(dataSetBus,this)
        // NAVIGATING TO BUS_MAIN_FRAGMENT Code inside RouteItemAdapter onViewHolder()

        return view

    }


}