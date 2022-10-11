package com.hx.mywallpaper

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.GnssStatus
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.location.LocationManagerCompat

class GPSLocationActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gpslocation)
        if ((ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED)&&(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)){
            getLocation()
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION),0x001)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("MissingPermission")
    private fun getLocation() {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val locationListener = LocationListener{
            Log.d("gps-latitude",it.latitude.toString())
            Log.d("gps-longitude",it.longitude.toString())
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,0f,locationListener)
        val gnssCallback = object : GnssStatus.Callback(){
            override fun onSatelliteStatusChanged(status: GnssStatus) {
                super.onSatelliteStatusChanged(status)
                Log.d("gps-gnss","共接收到卫星信号：${status.satelliteCount}个")
            }
        }
        locationManager.registerGnssStatusCallback(gnssCallback)
    }
}