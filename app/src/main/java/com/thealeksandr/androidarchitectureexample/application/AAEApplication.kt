package com.thealeksandr.androidarchitectureexample.application

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.thealeksandr.androidarchitectureexample.database.AAEDatabase

/**
 * Created by Aleksandr Nikiforov on 8/1/17.
 */
class AAEApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        database = Room.databaseBuilder(this, AAEDatabase::class.java, "aae-db").build()
    }


    companion object {
        var appContext: Context? = null
        var database: AAEDatabase? = null
    }


}