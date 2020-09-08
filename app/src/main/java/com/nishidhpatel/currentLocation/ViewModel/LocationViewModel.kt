package com.nishidhpatel.currentLocation.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

//N!SH!DHPATEL

class LocationViewModel(application: Application) : AndroidViewModel(application) {

    private val locationData = LocationLiveData(application)

    fun getLocationData() = locationData
}