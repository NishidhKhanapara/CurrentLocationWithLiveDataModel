package com.nishidhpatel.currentLocation.Utils

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.provider.Settings
import androidx.core.app.ActivityCompat

//N!SH!DHPATEL

class Utils(private val activity: Activity) {
    companion object {
        var LOCATION_REQ_CODE = 15
    }

    val locationManager = activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    fun checkGPS(): Boolean {

        val iSGpsOn =
            locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager!!.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
            )

        if (iSGpsOn) {
            return true
        } else {
            showMesaage()
            return false
        }
    }


    private fun showMesaage() {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                activity.startActivityForResult(
                    Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    , 11
                )
                dialog.cancel()
            }
            .setNegativeButton("No") { dialog, id ->
                dialog.cancel()
                activity.finish()
            }
        val alert: AlertDialog = builder.create()
        alert.show()
    }


    fun checkPermissionForLocation(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                true
            } else {
                // Show the permission request
                ActivityCompat.requestPermissions(
                    activity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_REQ_CODE
                )
                false
            }
        } else {
            true
        }
    }


}