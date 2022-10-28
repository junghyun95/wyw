package com.greedy.wouldyouwalk

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.graphics.Color
import android.location.Location
import android.location.LocationManager
import android.location.LocationRequest
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.greedy.wouldyouwalk.databinding.FragmentRouterecordBinding

class RouteRecordFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mView: MapView

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentRouterecordBinding

    var mLocationManager: LocationManager? = null
    var mLocationListener: LocationListener? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRouterecordBinding.inflate(inflater, container, false)

        mLocationManager = mainActivity.getSystemService(LOCATION_SERVICE) as LocationManager
        mLocationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                var lat = 0.0
                var lng = 0.0
                if (location != null) {
                    lat = location.latitude
                    lng = location.longitude
                    Log.d("현재 위치", "Lat ${lat}, Long ${lng}")
                }
                var currentLocation = LatLng(lat, lng)
            }
        }

        /* 화면에 지도 띄우기 */
        mView = binding.mapview as MapView
        mView.onCreate(savedInstanceState)
        mView.getMapAsync(this)
        
        return binding.root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val marker = LatLng(37.571919, 126.987316)
        googleMap.addMarker(MarkerOptions().position(marker).title("하이미디어컴퓨터학원 종로 캠퍼스"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 15f))

    }

    override fun onStart() {
        super.onStart()
        mView.onStart()
    }
    override fun onStop() {
        super.onStop()
        mView.onStop()
    }
    override fun onResume() {
        super.onResume()
        mView.onResume()
    }
    override fun onPause() {
        super.onPause()
        mView.onPause()
    }
    override fun onLowMemory() {
        super.onLowMemory()
        mView.onLowMemory()
    }
    override fun onDestroy() {
        mView.onDestroy()
        super.onDestroy()
    }

}