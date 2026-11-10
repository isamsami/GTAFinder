package com.example.assignment2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2.R
import com.example.assignment2.data.LocationItem

class LocationAdapter(
    private var locations: List<LocationItem>,
    private val onItemClick: (LocationItem) -> Unit
) : RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvAddress: TextView = itemView.findViewById(R.id.tvAddress)
        val tvCoordinates: TextView = itemView.findViewById(R.id.tvCoordinates)
        val tvCreatedDate: TextView = itemView.findViewById(R.id.tvCreatedDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_location, parent, false)
        return LocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val item = locations[position]
        holder.tvAddress.text = item.address
        holder.tvCoordinates.text = "Lat: ${item.latitude}, Lng: ${item.longitude}"
        holder.tvCreatedDate.text = "Created: ${item.createdDatetime}"

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int = locations.size

    fun updateData(newLocations: List<LocationItem>) {
        locations = newLocations
        notifyDataSetChanged()
    }
}
