package com.panjikrisnayasa.jsonrecyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import java.util.*

class EarthquakeAdapter constructor(private val context: Context, private val listEarthquake: ArrayList<Earthquake>) :
    RecyclerView.Adapter<EarthquakeAdapter.EarthquakeHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): EarthquakeHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_recycler_view_main, p0, false)
        return EarthquakeHolder(view)
    }

    override fun getItemCount(): Int {
        return listEarthquake.size
    }

    override fun onBindViewHolder(p0: EarthquakeHolder, p1: Int) {
        val (magnitude, location, date) = listEarthquake[p1]
        p0.mTextMagnitude.text = magnitude
        p0.mTextLocation.text = location
        p0.mTextDate.text = date

        p0.itemView.setOnClickListener {
            Toast.makeText(it.context, "Tapped", Toast.LENGTH_SHORT).show()
        }

//        val dateFormatter = SimpleDateFormat("EEE, MMM d, ''yy", Locale.getDefault())
//        val dateToDisplay = dateFormatter.format(date)
//        val timeFormatter = SimpleDateFormat("h:mm a", Locale.getDefault())
//        val timeToDisplay = timeFormatter.format(date)
//        p0.mTextDate.text = String.format("%s\n%s", dateToDisplay, timeToDisplay)
    }

    inner class EarthquakeHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mTextMagnitude: TextView = itemView.findViewById(R.id.text_item_recycler_view_main_magnitude)
        var mTextLocation: TextView = itemView.findViewById(R.id.text_item_recycler_view_main_location)
        var mTextDate: TextView = itemView.findViewById(R.id.text_item_recycler_view_main_date)
    }
}