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
import androidx.recyclerview.widget.RecyclerView
import com.modbot.bustle.R
import com.modbot.bustle.util.model.Bus


class BusStopTimelineItemAdapter(
    private val dataSetBus: List<Bus>
) : RecyclerView.Adapter<BusStopTimelineItemAdapter.ItemViewHolder>() {


    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val busName: TextView = view.findViewById(R.id.bus_name)
        val fromTo: TextView = view.findViewById(R.id.from_to)
        val time: TextView = view.findViewById(R.id.time)
        val timeRemaining: TextView = view.findViewById(R.id.time_remaining)

    }

    override fun getItemViewType(position: Int): Int {
        return when {
            dataSetBus[position].shortest_time == true -> 1
            dataSetBus[position].fast == true -> 2
            else -> 3
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val view: Int = when (viewType) {
            1 -> R.layout.item_bus_stop_bus_white
            2 -> R.layout.item_bus_stop_bus_blue
            else -> R.layout.item_bus_stop_bus_green
        }

        val adapterLayout = LayoutInflater.from(parent.context).inflate(view, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataSetBus[position]
        holder.busName.text = item.bus_name
        holder.fromTo.text = item.from_to
        holder.time.text = item.duration
        holder.timeRemaining.text = item.time_or_km_remaining
    }

    override fun getItemCount() = dataSetBus.size
}
