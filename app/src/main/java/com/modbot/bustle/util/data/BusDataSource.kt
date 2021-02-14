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

package com.modbot.bustle.util.data

import com.modbot.bustle.util.model.Bus
import com.modbot.bustle.util.model.Places


class BusDataSource {

    /**
     * BUS STATION TIMELINE:-
     * NON Required data for BUS
     * price
     */
    fun loadBuses(): List<Bus> {
        return listOf<Bus>(
            Bus(
                "Palayam Bus",
                "Poojapura - Palayam",
                "12:00pm",
                "13",
                "12",
                shortest_time = true
            ),
            Bus(
                "Pattom Bus Fast",
                "Kottayam - Trivandrum",
                "12:30pm",
                "20",
                "8",
                fast = true
            ),
            Bus(
                "Kottayam Bus",
                "Kottayam - Trivandrum",
                "12:40pm",
                "1hr 10",
                "10"
            ),
            Bus(
                "Kalampaly Bus Fast",
                "Kottayam - Trivandrum",
                "12:50pm",
                "1hr 30",
                "62",
                fast = true
            ),
            Bus(
                "Eastfort Bus",
                "Kottayam - Trivandrum",
                "1:00pm",
                "2hr 10",
                "47"
            )
        )
    }


    /**
     * ROUTE GENERATION SCREEN:-
     */
    fun loadRoute(): List<Bus> {
        return listOf<Bus>(
            Bus(
                "Palayam Bus",
                "Poojapura - Palayam",
                "12:00pm - 12:30pm",
                "13",
                "12",
                shortest_time = true
            ),
            Bus(
                null,   // NO BUS
                "Palayam - PMG",
                "12:30pm - 12:40pm",
                "0.75", "8"
            ),
            Bus(
                "Pattom Bus Fast",
                "PMG - Kesavadasapuram",
                "12:40pm - 12:50pm",
                "1hr 10",
                "10",
                fast = true,

                ),
            Bus(
                "Nalanchira",
                "Kesavadasapuram - Nalanchira",
                "12:50pm - 1:00pm",
                "1.3",
                "62",
                fast = true,
            ),
            Bus()   //Destination Reached
        )
    }

    /**
     * BUS DETAILED ROUTE:-
     * Required data for BUS
     * Bus Name
     * from_to
     * Route <Places>
     */
    fun loadBusDetailedRoute(): Bus {

        return Bus(
            "Palayam Bus",
            "Poojapura - Palayam",
            "12:00pm - 12:30pm",
            "13",
            "12",
            shortest_time = true,
            route = listOf<Places>(
                Places(
                    "Poojapura",
                    "12:00pm",
                    arrived = true,
                    delayed_time = "2 minutes"
                ),
                Places(
                    "DPI Junction",
                    "12:10pm",
                    arrived = true
                ),
                Places(
                    "Bakery Junction",
                    "12:15pm",
                ),
                Places(
                    "Panjapura",
                    "12:20pm",
                ),
                Places(
                    "Palayam",
                    "12:30pm",
                )
            )
        )

    }
}

