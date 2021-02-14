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

package com.modbot.bustle.util.model


data class Bus(
    val bus_name: String? = null,
    val from_to: String? = null,
    val duration: String? = null,
    val time_or_km_remaining: String? = null,
    val price: String? = null,
    val fast: Boolean? = false,
    val shortest_time: Boolean? = false,
    val route: List<Places>? = null
)

data class Places(
    val placeName: String? = null,
    val arrival_time: String? = null,
    val arrived: Boolean? = false,
    val delayed_time: String? = null,
)