package com.penghe.www.myframes.others

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import cn.almsound.www.baselibrary.BaseAppcompatActivity
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.penghe.www.myframes.R

class GoogleMapActivity : AppCompatActivity() {

    private var supportMapFragment: SupportMapFragment? = null
    private val onMapReadyCallback: OnMapReadyCallback = com.google.android.gms.maps.OnMapReadyCallback {
        map ->
        map.addMarker(MarkerOptions().position(LatLng(0.0, 0.0)).title("Marker"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.default_map)
        supportActionBar?.setTitle(R.string.google_map)
        supportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        supportMapFragment!!.getMapAsync(onMapReadyCallback)
    }
}
