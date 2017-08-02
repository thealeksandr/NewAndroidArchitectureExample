package com.thealeksandr.androidarchitectureexample.lifecycle

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.location.Location
import android.os.Bundle
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices

/**
* Created by Aleksandr Nikiforov on 8/2/17.
*/
class LocationObserver(context: Context) : LifecycleObserver,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private var googleApiClient: GoogleApiClient? = null
    private var value: Location? = null
    var listener: OnLocationListener? = null

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        googleApiClient?.connect()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onInactive() {
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
            listener?.onLocation(value)
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
                googleApiClient, LocationRequest(), this)
    }

    override fun onConnectionSuspended(p0: Int) {

    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }

    override fun onLocationChanged(p0: Location?) {
        value = p0
        listener?.onLocation(value)
    }

    init {
        googleApiClient = GoogleApiClient.Builder(context, this, this)
                .addApi(LocationServices.API)
                .build()
    }

    fun setOnLocationListener(onLocationListener: OnLocationListener) {
        this.listener = onLocationListener
    }

    interface OnLocationListener {
        fun onLocation(l: Location?)
    }
}