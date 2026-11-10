package com.example.assignment2.data

data class LocationItem(
    val id: Long = 0,
    val address: String,
    val latitude: Double,
    val longitude: Double,
    val createdDatetime: String = ""
)
