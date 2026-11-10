package com.example.assignment2.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor

class LocationDao(context: Context) {
    private val dbHelper = LocationDbHelper(context)

    fun insertLocation(address: String, latitude: Double, longitude: Double): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("address", address)
            put("latitude", latitude)
            put("longitude", longitude)
        }
        return db.insert(LocationDbHelper.TABLE_NAME, null, values)
    }

    fun updateLocation(id: Long, address: String, latitude: Double, longitude: Double): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("address", address)
            put("latitude", latitude)
            put("longitude", longitude)
        }
        return db.update(
            LocationDbHelper.TABLE_NAME,
            values,
            "id = ?",
            arrayOf(id.toString())
        )
    }

    fun deleteLocation(id: Long): Int {
        val db = dbHelper.writableDatabase
        return db.delete(
            LocationDbHelper.TABLE_NAME,
            "id = ?",
            arrayOf(id.toString())
        )
    }

    fun queryByAddress(searchQuery: String): List<LocationItem> {
        val list = mutableListOf<LocationItem>()
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.rawQuery(
            "SELECT * FROM ${LocationDbHelper.TABLE_NAME} WHERE address LIKE ? ORDER BY id DESC",
            arrayOf("%$searchQuery%")
        )

        cursor.use { c ->
            if (c.moveToFirst()) {
                do {
                    val id = c.getLong(c.getColumnIndexOrThrow("id"))
                    val address = c.getString(c.getColumnIndexOrThrow("address"))
                    val lat = c.getDouble(c.getColumnIndexOrThrow("latitude"))
                    val lng = c.getDouble(c.getColumnIndexOrThrow("longitude"))
                    val created = c.getString(c.getColumnIndexOrThrow("created_datetime"))
                    list.add(LocationItem(id, address, lat, lng, created))
                } while (c.moveToNext())
            }
        }
        return list
    }

    fun getAllLocations(): List<LocationItem> = queryByAddress("")
}
