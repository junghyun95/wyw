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
import com.google.android.gms.location.*
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.greedy.wouldyouwalk.databinding.FragmentRouterecordBinding

class RouteRecordFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mView: MapView

    lateinit var mainActivity: MainActivity
    lateinit var binding: FragmentRouterecordBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRouterecordBinding.inflate(inflater, container, false)


        /* 화면에 지도 띄우기 */
        mView = binding.mapview as MapView
        mView.onCreate(savedInstanceState)
        mView.getMapAsync(this)


        return binding.root
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {

        /* 현재 위치로 이동 설정(애뮬레이터는 위치 받을 수 없어서 구글 본사로 이동됨) */
        googleMap.isMyLocationEnabled = true

        /* 디폴트 위치 설정 */
        val defaultMarker = LatLng(37.571919, 126.987316)
        googleMap.addMarker(MarkerOptions().position(defaultMarker).title("하이미디어컴퓨터학원 종로 캠퍼스"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultMarker, 15f))

    }

    private fun onMarkerClick() {
        Log.d("마커이벤트", "동작확인!")
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