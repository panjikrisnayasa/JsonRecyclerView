package com.panjikrisnayasa.jsonrecyclerview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import org.json.JSONArray
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var earthquakeAdapter: EarthquakeAdapter
    private var list: ArrayList<Earthquake> = arrayListOf()
    private val url = "https://api.myjson.com/bins/h4syp"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRecyclerView = findViewById(R.id.recycler_view_main)
        mRecyclerView.setHasFixedSize(true)
        earthquakeAdapter = EarthquakeAdapter(applicationContext, list)
        mRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        mRecyclerView.adapter = earthquakeAdapter
        getData()
    }

    private fun getData() {
        MySingleton.getInstance(this.applicationContext).requestQueue
        val jsonArrayReq = JsonArrayRequest(url, Response.Listener<JSONArray> { response ->
            for (i in 0 until response.length()) {
                try {
                    val jsonObj = response.getJSONObject(i)
                    val earthquake = Earthquake()
                    earthquake.magnitude = jsonObj.getString("magnitude")
                    earthquake.location = jsonObj.getString("location")
                    earthquake.date = jsonObj.getString("date")
                    list.add(earthquake)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            earthquakeAdapter.notifyDataSetChanged()
        }, Response.ErrorListener {
            it.printStackTrace()
        })
        MySingleton.getInstance(this).addToRequestQueue(jsonArrayReq)
    }
}
