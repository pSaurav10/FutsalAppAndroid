package com.example.futsalapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.futsalapp.R
import com.example.futsalapp.api.ServiceBuilder
import com.example.futsalapp.model.Event

class EventAdapter(
    val context: Context,
    val eventlist: MutableList<Event>
): RecyclerView.Adapter<EventAdapter.EventViewHolder>() {
    class EventViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvTitle: TextView
        val ivImage: ImageView
        init {
            tvTitle = view.findViewById(R.id.tvTitle)
            ivImage = view.findViewById(R.id.ivImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_layout, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = eventlist[position]
        holder.tvTitle.text = event.name

        val imagePath = ServiceBuilder.loadImagePath() + event.image
        Glide.with(context)
            .load(imagePath)
            .fitCenter()
            .into(holder.ivImage)

//        Single Events
    }

    override fun getItemCount(): Int {
        return eventlist.size
    }
}