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
import com.example.futsalapp.model.Futsal

class FutsalAdapter(
        val context: Context,
        val futsallist: ArrayList<Futsal>
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

        Glide.with(context)
                .load(futsal.image)
                .into(holder.ivImage)
    }

    override fun getItemCount(): Int {
        return futsallist.size
    }
}