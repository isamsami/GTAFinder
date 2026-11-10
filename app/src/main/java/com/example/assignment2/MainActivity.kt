package com.example.assignment2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.data.GtaData
import com.example.assignment2.data.LocationDao
import com.example.assignment2.data.LocationItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private lateinit var locationDao: LocationDao
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnAddLocation: Button
    private var locationList = mutableListOf<LocationItem>()

    private lateinit var adapter: LocationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        locationDao = LocationDao(this)

        // Pre-populate 100 GTA locations if empty
        seedInitialDataIfNeeded()

        // Setup Map Fragment
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        searchView = findViewById(R.id.searchView)
        recyclerView = findViewById(R.id.recyclerView)
        btnAddLocation = findViewById(R.id.btnAddLocation)

        recyclerView.layoutManager = LinearLayoutManager(this)

        //search for sql data
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                performQuery(query.orEmpty())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                performQuery(newText.orEmpty())
                return true
            }
        })

        btnAddLocation.setOnClickListener {
            val intent = Intent(this, ManageLocationActivity::class.java)
            startActivity(intent)
        }

        adapter = LocationAdapter(locationList) { selectedLocation ->
            // Click action: Open ManageLocationActivity in Edit Mode
            val intent = Intent(this, ManageLocationActivity::class.java).apply {
                putExtra(ManageLocationActivity.EXTRA_LOCATION_ID, selectedLocation.id)
                putExtra(ManageLocationActivity.EXTRA_ADDRESS, selectedLocation.address)
                putExtra(ManageLocationActivity.EXTRA_LATITUDE, selectedLocation.latitude)
                putExtra(ManageLocationActivity.EXTRA_LONGITUDE, selectedLocation.longitude)
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter
    }

    //functions for resume and map
    override fun onResume() {
        super.onResume()
        performQuery(searchView.query.toString())
    }

    // Default camera position over GTA area (Toronto)
    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        val gtaCenter = LatLng(43.6532, -79.3832)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gtaCenter, 9f))
        performQuery("")
    }

    //perform query function
    private fun performQuery(query: String) {
        locationList.clear()
        locationList.addAll(locationDao.queryByAddress(query))

        // Update map markers
        if (::googleMap.isInitialized) {
            googleMap.clear()
            for (loc in locationList) {
                val point = LatLng(loc.latitude, loc.longitude)
                googleMap.addMarker(
                    MarkerOptions()
                        .position(point)
                        .title(loc.address)
                        .snippet("Lat: ${loc.latitude}, Lng: ${loc.longitude}")
                )
            }
            // Zoom to first result if found
            if (locationList.isNotEmpty() && query.isNotEmpty()) {
                val first = locationList[0]
                val point = LatLng(first.latitude, first.longitude)
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(point, 13f))
            }

            // adapter to update managelocationactivity
            adapter.updateData(locationList)
        }
    }

    //method to use GtaData fill
    private fun seedInitialDataIfNeeded() {
        if (locationDao.getAllLocations().isEmpty()) {
            for ((address, lat, lng) in GtaData.locations) {
                locationDao.insertLocation(address, lat, lng)
            }
        }
    }
}