package com.thealeksandr.androidarchitectureexample.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.thealeksandr.androidarchitectureexample.database.daos.GeoLocationDao
import com.thealeksandr.androidarchitectureexample.database.models.GeoLocation

/**
* Created by Aleksandr Nikiforov on 8/1/17.
*/
@Database(entities = arrayOf(GeoLocation::class), version = 1)
abstract class AAEDatabase : RoomDatabase() {

    abstract fun geoLocationDao(): GeoLocationDao

}