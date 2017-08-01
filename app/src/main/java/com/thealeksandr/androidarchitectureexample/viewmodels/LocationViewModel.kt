package com.thealeksandr.androidarchitectureexample.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.location.Location

/**
 * Created by Aleksandr Nikiforov on 8/1/17.
 */
class LocationViewModel: ViewModel() {

    private var locations: MutableLiveData<List<Location>>? = MutableLiveData()

    fun getLocations(): LiveData<List<Location>> {
        if (locations == null) {
            locations = MutableLiveData()
            loadLocations()
        }
        return locations!!
    }

    private fun loadLocations() {

    }

}