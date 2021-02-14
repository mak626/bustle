package com.modbot.bustle.bus_main

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.modbot.bustle.R
import com.modbot.bustle.util.data.BusStopDataSource
import com.modbot.bustle.util.model.BusStop


class BusMainFragment : Fragment(), OnMapReadyCallback {

    private lateinit var maps: GoogleMap
    private lateinit var dataSetBusStops: List<BusStop>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bus_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        val seeDeatiledButton = view.findViewById<Button>(R.id.button_see_detailed_timetable)

        seeDeatiledButton.setOnClickListener {
            findNavController().navigate(R.id.action_busMainFragment_to_busDetailsFragment)
        }
    }

    // Map Code
    override fun onMapReady(googleMap: GoogleMap?) {

        if (googleMap != null) {
            this.maps = googleMap
        }

        dataSetBusStops = BusStopDataSource().loadBusStop()

        val busIcon = getBusIcon()
        val destIcon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
        val startIcon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)

        maps.addMarker(
            MarkerOptions().position(dataSetBusStops[0].coordinates).title(dataSetBusStops[0].name)
                .icon(
                    startIcon
                )
        )
        maps.addMarker(
            MarkerOptions().position(dataSetBusStops[1].coordinates).title(dataSetBusStops[0].name)
                .icon(
                    busIcon
                )
        )
        maps.addMarker(
            MarkerOptions().position(dataSetBusStops[2].coordinates).title(dataSetBusStops[0].name)
                .icon(
                    destIcon
                )
        )

        maps.moveCamera(CameraUpdateFactory.newLatLng(dataSetBusStops[0].coordinates))
        maps.setMinZoomPreference(14F)
        maps.isMyLocationEnabled = true

    }

    fun getBusIcon() : BitmapDescriptor
    {
        val bitmapdraw : BitmapDrawable =  ResourcesCompat.getDrawable(resources,R.drawable.icon_bus_marker,null) as BitmapDrawable//resources.getDrawable(R.drawable.bus_icon) as BitmapDrawable
        val busMarker = Bitmap.createScaledBitmap(bitmapdraw.bitmap, 72 , 100, false)
        return BitmapDescriptorFactory.fromBitmap(busMarker)
    }
}