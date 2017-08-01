package com.thealeksandr.androidarchitectureexample.database.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.thealeksandr.androidarchitectureexample.database.models.GeoLocation

/**
* Created by Aleksandr Nikiforov on 8/1/17.
*/
@Dao
interface GeoLocationDao {

    /**
     * Get locations LiveData.
     * Can be called from the main thread.
     * @return LiveData of GroLocation list.
     */
    @Query("SELECT * FROM geoLocation ORDER BY timestamp DESC")
    fun getLocationsLiveData(): LiveData<List<GeoLocation>>

    /**
     * Get locations list.
     * Can be called from the main thread.
     * @return List of GroLocation objects.
     */
    @Query("SELECT * FROM geoLocation ORDER BY timestamp DESC")
    fun getLocations(): List<GeoLocation>

    /**
     * Insert new locations.
     * Have to ba called in background thread.
     * @param location GeoLocation object.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg location: GeoLocation)


}