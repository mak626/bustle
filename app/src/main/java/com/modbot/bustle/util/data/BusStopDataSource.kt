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

import com.google.android.gms.maps.model.LatLng
import com.modbot.bustle.util.model.BusStop

class BusStopDataSource {

    fun loadBusStop(): List<BusStop> {
        return listOf<BusStop>(
            BusStop("Pattom Bus Stop", LatLng(8.519255458926354, 76.94213829108045)),
            BusStop("Kesavadasapuram Bus Stop", LatLng(8.529888098530243, 76.9384947392172)),
            BusStop("Uloor Bus Stop", LatLng(8.530760203594207, 76.92875247114044)),
            BusStop("Peroorkada Bus Stop", LatLng(8.550587341280776, 76.961917465283)),
            BusStop("KSRTC BUS CITY MAIN DEPOT", LatLng(8.496312413817341, 76.94337890491809)),
            BusStop("Thampanoor Bus Stop", LatLng(8.490283343063247, 76.95376774979455)),
            BusStop("Palayam Bus Stop", LatLng(8.52415490459861, 76.95024535995798)),
            BusStop("PMG junction", LatLng(8.523475843556856, 76.948185423446)),
            BusStop("Vazhuthacaud Bus Stop", LatLng(8.520080520257313, 76.96191833352577)),
            BusStop("KSRTC Bus Terminal Complex", LatLng(8.491738096765852, 76.9515402281844))
        )

    }
}

