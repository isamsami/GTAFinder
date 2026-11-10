package com.example.assignment2

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment2.R
import com.example.assignment2.data.LocationDao
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ManageLocationActivity : AppCompatActivity() {

    private lateinit var tvTitle: TextView
    private lateinit var tilAddress: TextInputLayout
    private lateinit var tilLatitude: TextInputLayout
    private lateinit var tilLongitude: TextInputLayout
    private lateinit var etAddress: TextInputEditText
    private lateinit var etLatitude: TextInputEditText
    private lateinit var etLongitude: TextInputEditText
    private lateinit var btnSave: Button
    private lateinit var btnDelete: Button
    private lateinit var btnCancel: Button

    private lateinit var locationDao: LocationDao
    private var locationId: Long = -1L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_manage_location)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        locationDao = LocationDao(this)

        // Bind Views
        tvTitle = findViewById(R.id.tvTitle)
        tilAddress = findViewById(R.id.tilAddress)
        tilLatitude = findViewById(R.id.tilLatitude)
        tilLongitude = findViewById(R.id.tilLongitude)
        etAddress = findViewById(R.id.etAddress)
        etLatitude = findViewById(R.id.etLatitude)
        etLongitude = findViewById(R.id.etLongitude)
        btnSave = findViewById(R.id.btnSave)
        btnDelete = findViewById(R.id.btnDelete)
        btnCancel = findViewById(R.id.btnCancel)

        // Check if intent contains ID (Edit Mode vs Add Mode)
        if (intent.hasExtra(EXTRA_LOCATION_ID)) {
            locationId = intent.getLongExtra(EXTRA_LOCATION_ID, -1L)
            val address = intent.getStringExtra(EXTRA_ADDRESS) ?: ""
            val lat = intent.getDoubleExtra(EXTRA_LATITUDE, 0.0)
            val lng = intent.getDoubleExtra(EXTRA_LONGITUDE, 0.0)

            tvTitle.text = "Edit Location"
            btnSave.text = "Update Location"
            btnDelete.visibility = View.VISIBLE

            etAddress.setText(address)
            etLatitude.setText(lat.toString())
            etLongitude.setText(lng.toString())
        } else {
            tvTitle.text = "Add New Location"
            btnSave.text = "Save Location"
            btnDelete.visibility = View.GONE
        }

        // Save / Update Action
        btnSave.setOnClickListener {
            saveLocation()
        }

        // Delete Action
        btnDelete.setOnClickListener {
            if (locationId != -1L) {
                locationDao.deleteLocation(locationId)
                Toast.makeText(this, "Location deleted successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        // Cancel Action
        btnCancel.setOnClickListener {
            finish()
        }
    }

    private fun saveLocation() {
        val addressText = etAddress.text.toString().trim()
        val latText = etLatitude.text.toString().trim()
        val lngText = etLongitude.text.toString().trim()

        // Validation
        var isValid = true

        if (addressText.isEmpty()) {
            tilAddress.error = "Address is required"
            isValid = false
        } else {
            tilAddress.error = null
        }

        val latitude = latText.toDoubleOrNull()
        if (latitude == null || latitude < -90 || latitude > 90) {
            tilLatitude.error = "Enter valid latitude (-90 to 90)"
            isValid = false
        } else {
            tilLatitude.error = null
        }

        val longitude = lngText.toDoubleOrNull()
        if (longitude == null || longitude < -180 || longitude > 180) {
            tilLongitude.error = "Enter valid longitude (-180 to 180)"
            isValid = false
        } else {
            tilLongitude.error = null
        }

        if (!isValid) return

        if (locationId == -1L) {
            // Add Mode
            locationDao.insertLocation(addressText, latitude!!, longitude!!)
            Toast.makeText(this, "Location added successfully", Toast.LENGTH_SHORT).show()
        } else {
            // Update Mode
            locationDao.updateLocation(locationId, addressText, latitude!!, longitude!!)
            Toast.makeText(this, "Location updated successfully", Toast.LENGTH_SHORT).show()
        }

        finish()
    }

    companion object {
        const val EXTRA_LOCATION_ID = "extra_location_id"
        const val EXTRA_ADDRESS = "extra_address"
        const val EXTRA_LATITUDE = "extra_latitude"
        const val EXTRA_LONGITUDE = "extra_longitude"
    }
}