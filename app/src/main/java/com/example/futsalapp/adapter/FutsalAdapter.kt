package com.example.futsalapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.futsalapp.R
import com.example.futsalapp.api.ServiceBuilder
import com.example.futsalapp.entity.FutsalItem
import com.example.futsalapp.model.Futsal
import kotlin.math.log

class FutsalAdapter(
        val context: Context,
        val futsallist: List<FutsalItem>
): RecyclerView.Adapter<FutsalAdapter.FutsalViewHolder> ()
{
    class FutsalViewHolder(view: View):
            RecyclerView.ViewHolder(view){
                val tvTitle: TextView
                val ivImage: ImageView
        init{
            tvTitle = view.findViewById(R.id.tvTitle)
            ivImage = view.findViewById(R.id.ivImage)
        }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FutsalViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.futsal_layout, parent, false)
        return FutsalViewHolder(view)
    }

    override fun onBindViewHolder(holder: FutsalViewHolder, position: Int) {
        val futsal = futsallist[position]
        holder.tvTitle.text = futsal.name

        val imagePath = ServiceBuilder.loadImagePath() + futsal.image
        println("image $imagePath")
        Glide.with(context)
                .load(imagePath)
                .fitCenter()
                .into(holder.ivImage)
    }

    override fun getItemCount(): Int {
        return futsallist.size
    }
}