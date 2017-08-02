package com.thealeksandr.androidarchitectureexample.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import com.thealeksandr.androidarchitectureexample.application.AAEApplication
import com.thealeksandr.androidarchitectureexample.database.models.GeoLocation

/**
* Created by Aleksandr Nikiforov on 8/1/17.
*/
class GeoLocationViewModel : ViewModel() {

    private var locations: MutableLiveData<List<GeoLocation>>? = null

    fun getLocations(): LiveData<List<GeoLocation>> {
        if (locations == null) {
            locations = MutableLiveData()
            reloadLocations()
        }
        return locations!!
    }


    fun reloadLocations() {
        AsyncTask.execute {
            val locationsList = AAEApplication.database?.geoLocationDao()?.getLocations()
            Handler(Looper.getMainLooper()).post { locations?.value = locationsList }
        }
    }

}