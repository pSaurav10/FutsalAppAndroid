package com.example.futsalapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.futsalapp.FutsaldetailActivity
import com.example.futsalapp.R
import com.example.futsalapp.api.ServiceBuilder
import com.example.futsalapp.model.Futsal
import com.google.android.material.internal.ContextUtils.getActivity
import kotlin.math.log

class FutsalAdapter(
        val context: Context,
        val futsallist: MutableList<Futsal>
): RecyclerView.Adapter<FutsalAdapter.FutsalViewHolder> ()
{
    class FutsalViewHolder(view: View):
            RecyclerView.ViewHolder(view){
                val tvTitle: TextView
                val ivImage: ImageView
                val cardview: CardView
        init{
            tvTitle = view.findViewById(R.id.tvTitle)
            ivImage = view.findViewById(R.id.ivImage)
            cardview = view.findViewById(R.id.cardview)
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

        holder.cardview.setOnClickListener {
            val intent = Intent (context, FutsaldetailActivity::class.java)
            .putExtra("futsal", futsal)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return futsallist.size
    }
}