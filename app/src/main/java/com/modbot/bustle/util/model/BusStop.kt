package com.modbot.bustle.util.model

import com.google.android.gms.maps.model.LatLng

data class BusStop(
    val name: String,
    val coordinates: LatLng
)