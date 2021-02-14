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
import com.modbot.bustle.util.model.Places


class BusDetailedRouteItemAdapter(
    private val dataSetPlaces: List<Places>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ArrivedPlaceViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val placeName: TextView = view.findViewById(R.id.place_name)
        val time: TextView = view.findViewById(R.id.time)
        val delayTime: TextView = view.findViewById(R.id.delay_time)
        val delayLabel: TextView = view.findViewById(R.id.delay_label)

    }

    class UpcomingViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val placeName: TextView = view.findViewById(R.id.place_name)
        val time: TextView = view.findViewById(R.id.time)
    }


    override fun getItemViewType(position: Int): Int {
        return when (dataSetPlaces[position].arrived) {
            true -> 1
            else -> 2
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = LayoutInflater.from(parent.context)
        return when (viewType) {
            1 -> {
                val adapterLayout = layout.inflate(R.layout.item_bus_place_arrived_grey, parent, false)
                ArrivedPlaceViewHolder(adapterLayout)
            }
            else -> {
                val adapterLayout = layout.inflate(R.layout.item_bus_place_upcoming_white, parent, false)
                UpcomingViewHolder(adapterLayout)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = dataSetPlaces[position]

        if (getItemViewType(position) == 1) {
            holder as ArrivedPlaceViewHolder
            holder.placeName.text = item.placeName
            holder.time.text = item.arrival_time
            if(item.delayed_time ==null){
                holder.delayLabel.visibility = TextView.INVISIBLE
                holder.delayTime.visibility = TextView.INVISIBLE
            }
            else {
                holder.delayTime.text = item.delayed_time
            }

        } else {
            holder as UpcomingViewHolder
            holder.placeName.text = item.placeName
            holder.time.text = item.arrival_time
        }

    }

    override fun getItemCount() = dataSetPlaces.size

}
