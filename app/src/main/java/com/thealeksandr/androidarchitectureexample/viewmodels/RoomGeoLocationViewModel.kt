
package com.thealeksandr.androidarchitectureexample.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.thealeksandr.androidarchitectureexample.application.AAEApplication
import com.thealeksandr.androidarchitectureexample.database.models.GeoLocation

/**
* Created by Aleksandr Nikiforov on 8/1/17.
*/
class RoomGeoLocationViewModel : ViewModel() {

    private var geoLocations: LiveData<List<GeoLocation>>? = null

    fun getLocations(): LiveData<List<GeoLocation>> {
        if (geoLocations == null) {
            geoLocations = AAEApplication.database?.geoLocationDao()?.getLocationsLiveData()
        }
        return geoLocations!!
    }

}