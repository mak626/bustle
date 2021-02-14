package com.modbot.bustle.home

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
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*
import com.modbot.bustle.R
import com.modbot.bustle.util.data.BusStopDataSource
import com.modbot.bustle.util.model.BusStop


class HomeFragment : Fragment(), OnMapReadyCallback {

    private lateinit var maps: GoogleMap
    private lateinit var dataSetBusStops: List<BusStop>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        val searchButton = view.findViewById<Button>(R.id.search_button)

        searchButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_routeFragment)
        }

    }

    // Map Code
    override fun onMapReady(googleMap: GoogleMap?) {

        if (googleMap != null) {
            this.maps = googleMap
        }

        dataSetBusStops = BusStopDataSource().loadBusStop()
        val busIcon = getBusIcon()

        for (item in dataSetBusStops) {
            maps.addMarker(
                MarkerOptions().position(item.coordinates).title(item.name).icon(busIcon)
            )
        }

        maps.moveCamera(CameraUpdateFactory.newLatLng(dataSetBusStops[0].coordinates))
        maps.setMinZoomPreference(14F)

        // Bus Stop Clicking
        maps.setOnMarkerClickListener {
            dataSetBusStops = BusStopDataSource().loadBusStop()
            if (it.title.equals(dataSetBusStops[0].name)) {
                findNavController().navigate(R.id.action_homeFragment_to_busStopTimelineFragment)
            }
            return@setOnMarkerClickListener false

        }

        //Enable Gps Icon
        maps.isMyLocationEnabled = true

    }


    private fun getBusIcon(): BitmapDescriptor {
        val bitmapdraw: BitmapDrawable = ResourcesCompat.getDrawable(
            resources,
            R.drawable.icon_bus_marker,
            null
        ) as BitmapDrawable//resources.getDrawable(R.drawable.bus_icon) as BitmapDrawable
        val busMarker = Bitmap.createScaledBitmap(bitmapdraw.bitmap, 72, 100, false)
        return BitmapDescriptorFactory.fromBitmap(busMarker)
    }

}