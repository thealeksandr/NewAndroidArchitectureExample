package com.thealeksandr.androidarchitectureexample.fragments

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thealeksandr.androidarchitectureexample.R
import com.thealeksandr.androidarchitectureexample.adapters.GeoLocationAdapter
import com.thealeksandr.androidarchitectureexample.application.AAEApplication
import com.thealeksandr.androidarchitectureexample.database.models.GeoLocation
import com.thealeksandr.androidarchitectureexample.livedata.LocationLiveData
import com.thealeksandr.androidarchitectureexample.viewmodels.RoomGeoLocationViewModel

/**
* Created by Aleksandr Nikiforov on 8/2/17.
*/
class LiveDataFragment: LifecycleFragment() {

    private var adapter: GeoLocationAdapter? = null
    private var recyclerView: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_locations, container, false)
        adapter = GeoLocationAdapter(activity)

        recyclerView = view?.findViewById(R.id.recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        recyclerView?.adapter = adapter
        init()
        return view
    }

    fun init() {
        val locationLiveData = LocationLiveData(activity)
        locationLiveData.observe(this, Observer {
            l -> AsyncTask.execute {
                if (l != null) {
                    AAEApplication.database?.geoLocationDao()?.insertAll(
                        GeoLocation(l.latitude, l.longitude, l.time)) }
            }
        })

        val viewModel = ViewModelProviders.of(this).get(RoomGeoLocationViewModel::class.java)
        viewModel.getLocations().observe(this, Observer {
            data -> adapter?.geoLocations = data
        })
    }
}