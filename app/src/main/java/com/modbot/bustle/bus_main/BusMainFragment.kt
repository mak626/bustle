package com.modbot.bustle.bus_main

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
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
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.modbot.bustle.R
import com.modbot.bustle.util.data.BusStopDataSource
import com.modbot.bustle.util.model.BusStop


class BusMainFragment : Fragment(), OnMapReadyCallback, LocationListener {

    private lateinit var maps: GoogleMap
    private lateinit var dataSetBusStops: List<BusStop>
    private lateinit var location: LocationManager

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

        val seeDetailedButton = view.findViewById<Button>(R.id.button_see_detailed_timetable)
        seeDetailedButton.setOnClickListener {
            findNavController().navigate(R.id.action_busMainFragment_to_busDetailsFragment)
        }

    }

    /**
     * Map Functions
     */
    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap?) {

        if (googleMap != null) {
            this.maps = googleMap
        }

        dataSetBusStops = BusStopDataSource().loadBusStop()

        //Getting Current Location
        location = this.context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        location.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            2000,
            10F, this
        )

        maps.setMinZoomPreference(14F)
        //     maps.isMyLocationEnabled = true

    }

    private fun getBusIcon(): BitmapDescriptor {
        val bitMapDraw: BitmapDrawable = ResourcesCompat.getDrawable(
            resources,
            R.drawable.icon_bus_marker,
            null
        ) as BitmapDrawable//resources.getDrawable(R.drawable.bus_icon) as BitmapDrawable
        val busMarker = Bitmap.createScaledBitmap(bitMapDraw.bitmap, 97, 70, false)
        return BitmapDescriptorFactory.fromBitmap(busMarker)
    }

    override fun onLocationChanged(location: Location) {
        val busIcon = getBusIcon()
        val destIcon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
        val startIcon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)
        val lastLocation = LatLng(location.latitude, location.longitude)

        val marker = MarkerOptions().position(lastLocation).title("Palayam Bus").icon(busIcon)
        val startMarker = MarkerOptions().position(dataSetBusStops[1].coordinates).title(dataSetBusStops[1].name).icon(startIcon)
        val destinationMarker =   MarkerOptions().position(dataSetBusStops[2].coordinates).title(dataSetBusStops[2].name).icon(destIcon)

        maps.clear()
        maps.addMarker(marker)
        maps.addMarker(startMarker)
        maps.addMarker(destinationMarker)
        maps.moveCamera(CameraUpdateFactory.newLatLngZoom(lastLocation,11.0F))

    }
}