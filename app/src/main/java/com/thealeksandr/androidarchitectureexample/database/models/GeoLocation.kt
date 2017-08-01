package com.thealeksandr.androidarchitectureexample.database.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
* Created by Aleksandr Nikiforov on 8/1/17.
*/
@Entity
class GeoLocation {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var latitude: Double?  = null
    var longitude: Double? = null
    var timestamp: Long? = null



    constructor()

    constructor(latitude: Double?, longitude: Double?, timestamp: Long?) {
        this.latitude = latitude
        this.longitude = longitude
        this.timestamp = timestamp
    }
}