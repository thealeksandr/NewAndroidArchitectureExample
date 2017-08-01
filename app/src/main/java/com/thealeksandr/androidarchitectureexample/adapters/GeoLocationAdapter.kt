
package com.thealeksandr.androidarchitectureexample.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.thealeksandr.androidarchitectureexample.R
import com.thealeksandr.androidarchitectureexample.application.AAEApplication
import com.thealeksandr.androidarchitectureexample.database.models.GeoLocation
import java.util.*

/**
* Created by Aleksandr Nikiforov on 8/1/17.
*/
class GeoLocationAdapter(var context: Context?) :
        RecyclerView.Adapter<GeoLocationAdapter.GeoLocationViewHolder>() {

    private val dateFormat = android.text.format.DateFormat.getDateFormat(AAEApplication.appContext)
    private val timeFormat = android.text.format.DateFormat.getTimeFormat(AAEApplication.appContext)

    var geoLocations: List<GeoLocation>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onBindViewHolder(holder: GeoLocationViewHolder?, position: Int) {
        val geoLocation = geoLocations?.get(position)
        val latitude = geoLocation?.latitude ?: 0
        val longitude = geoLocation?.longitude ?: 0
        val time = geoLocation?.timestamp ?: 0

        holder?.locationTextView?.text = String.format("%f, %f", latitude, longitude)
        val date = Date(time)
        holder?.dateTextView?.text =
                if(DateUtils.isToday(time))
                    timeFormat.format(date)
                else
                    dateFormat.format(date)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): GeoLocationViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.view_location, parent, false)
        return GeoLocationViewHolder(view)
    }

    override fun getItemCount(): Int {
        return geoLocations?.size ?: 0
    }

    class GeoLocationViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        val locationTextView: TextView? = itemView?.findViewById(R.id.location_text_view)
        val dateTextView: TextView? = itemView?.findViewById(R.id.date_text_view)

    }

}