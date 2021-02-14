/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.modbot.bustle.util.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.modbot.bustle.R
import com.modbot.bustle.util.model.Bus


class RouteItemAdapter(
    private val dataSetBus: List<Bus>,
    private val fragment: Fragment
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class BusViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val busName: TextView = view.findViewById(R.id.bus_name)
        val fromTo: TextView = view.findViewById(R.id.from_to)
        val time: TextView = view.findViewById(R.id.time_from_to)
        val timeRemaining: TextView = view.findViewById(R.id.time_remaining)
        val price: TextView = view.findViewById(R.id.price)

    }

    class NoBusViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val fromTo: TextView = view.findViewById(R.id.from_to)
        val time: TextView = view.findViewById(R.id.time_from_to)
        val distance: TextView = view.findViewById(R.id.distance)
    }

    class DestinationViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        // No Datatype for destination type item
    }


    override fun getItemViewType(position: Int): Int {
        return when {
            dataSetBus[position].bus_name == null && dataSetBus[position].from_to == null -> 3
            dataSetBus[position].bus_name == null -> 2
            else -> 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = LayoutInflater.from(parent.context)
        return when (viewType) {
            1 -> {
                val adapterLayout = layout.inflate(R.layout.item_route_bus, parent, false)
                BusViewHolder(adapterLayout)
            }
            2 -> {
                val adapterLayout = layout.inflate(R.layout.item_route_no_bus, parent, false)
                NoBusViewHolder(adapterLayout)
            }
            else -> {
                val adapterLayout =
                    layout.inflate(R.layout.item_route_destination_reached, parent, false)
                DestinationViewHolder(adapterLayout)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = dataSetBus[position]

        // NAVIGATING TO BUS_MAIN_FRAGMENT
        holder.itemView.setOnClickListener {
            val clickedBusName = "Palayam Bus"
            if (item.bus_name.equals(clickedBusName))
                fragment.findNavController().navigate(R.id.action_routeFragment_to_busMainFragment)
        }

        if (getItemViewType(position) == 1) {
            holder as BusViewHolder
            holder.busName.text = item.bus_name
            holder.fromTo.text = item.from_to
            holder.time.text = item.duration
            holder.timeRemaining.text = item.time_or_km_remaining
            holder.price.text = item.price
        } else if (getItemViewType(position) == 2) {
            holder as NoBusViewHolder
            holder.fromTo.text = item.from_to
            holder.time.text = item.duration
        } else {
            holder as DestinationViewHolder
        }

    }

    override fun getItemCount() = dataSetBus.size

}
