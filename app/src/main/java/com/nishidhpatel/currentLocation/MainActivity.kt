package com.nishidhpatel.currentLocation

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nishidhpatel.currentLocation.Utils.Utils
import com.nishidhpatel.currentLocation.Utils.Utils.Companion.LOCATION_REQ_CODE
import com.nishidhpatel.currentLocation.ViewModel.LocationViewModel
import kotlinx.android.synthetic.main.activity_main.*

//N!SH!DHPATEL

class MainActivity : AppCompatActivity() {


    private lateinit var locationViewModel: LocationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //initViewModel
        locationViewModel = ViewModelProvider(this).get(LocationViewModel::class.java)

        //checkpermission
        if (Utils(this).checkPermissionForLocation()) {
            startLocation()
        }

    }

    fun startLocation() {

        if (Utils(this).checkGPS()) {
            locationViewModel.getLocationData().observe(this, Observer {
                tvLatLong.text = resources.getString(R.string.strLatLong) + it.latitude + " " + it.longitude
            })

        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == LOCATION_REQ_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocation()
            } else {
                Toast.makeText(this@MainActivity, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onRestart() {
        super.onRestart()
        startLocation()
    }


}

