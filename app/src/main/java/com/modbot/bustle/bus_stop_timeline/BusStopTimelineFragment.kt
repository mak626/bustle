package com.modbot.bustle.bus_stop_timeline

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.modbot.bustle.R
import com.modbot.bustle.util.adapter.BusStopTimelineItemAdapter
import com.modbot.bustle.util.data.BusDataSource


class BusStopTimelineFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bus_stop_timeline, container, false)
        val myDataset = BusDataSource().loadBuses()

        val recyclerView = view.findViewById<RecyclerView>(R.id.bus_stop_recycler)
        recyclerView.adapter = BusStopTimelineItemAdapter(myDataset)


        return view
    }

}