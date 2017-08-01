package com.thealeksandr.androidarchitectureexample.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import com.thealeksandr.androidarchitectureexample.R
import com.thealeksandr.androidarchitectureexample.adapters.GeoLocationAdapter
import com.thealeksandr.androidarchitectureexample.application.AAEApplication
import com.thealeksandr.androidarchitectureexample.database.models.GeoLocation
import com.thealeksandr.androidarchitectureexample.livedata.LocationLiveData
import com.thealeksandr.androidarchitectureexample.viewmodels.RoomGeoLocationViewModel

class MainActivity : LifecycleAppCompatActivity() {

    private var adapter: GeoLocationAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = GeoLocationAdapter(this)

        val recyclerView = findViewById(R.id.recycler_view) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


        val locationLiveData = LocationLiveData(this)
        locationLiveData.observe(this, Observer {
            l -> Runnable {
                if (l != null) {
                    AAEApplication.database?.geoLocationDao()?.insertAll(
                            GeoLocation(l.latitude, l.longitude, l.time))
                }
            }
        })

        val viewModel = ViewModelProviders.of(this).get(RoomGeoLocationViewModel::class.java)
        viewModel.getLocations().observe(this, Observer {
            data -> adapter?.geoLocations = data
        })

    }




}
