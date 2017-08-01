package com.thealeksandr.androidarchitectureexample.livedata

import android.arch.lifecycle.LiveData
import android.content.Context
import android.location.Location
import android.os.Bundle
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices

/**
* Created by Aleksandr Nikiforov on 8/1/17.
*/
class LocationLiveData(context: Context) : LiveData<Location>(),
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private var googleApiClient: GoogleApiClient? = null

    override fun onActive() {
        googleApiClient?.connect()
    }

    override fun onInactive() {
        if (googleApiClient?.isConnected ?: false) {
            LocationServices.FusedLocationApi.removeLocationUpdates(
                    googleApiClient, this)
        }
        googleApiClient?.disconnect()
    }

    override fun onConnected(p0: Bundle?) {
        // Try to immediately find a location
        val lastLocation = LocationServices.FusedLocationApi
                .getLastLocation(googleApiClient)
        if (lastLocation != null) {
            value = lastLocation
        }
        // Request updates if there’s someone observing
        if (hasActiveObservers()) {
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    googleApiClient, LocationRequest(), this)
        }
    }

    override fun onConnectionSuspended(p0: Int) {

    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }

    override fun onLocationChanged(p0: Location?) {
        value = p0
    }

    init {
        googleApiClient = GoogleApiClient.Builder(context, this, this)
                .addApi(LocationServices.API)
                .build()
    }


}