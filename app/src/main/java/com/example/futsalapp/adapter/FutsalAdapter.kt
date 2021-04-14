package com.example.futsalapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.futsalapp.FutsaldetailActivity
import com.example.futsalapp.R
import com.example.futsalapp.api.ServiceBuilder
import com.example.futsalapp.model.Futsal
import com.example.futsalapp.repository.UserRepository
import com.google.android.material.internal.ContextUtils.getActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val userrepo = UserRepository()
                    val response = userrepo.getUser()
                    val success = response.success
                    if (success == true){
                        val userdata = response.data!!
                        withContext(Main){
                        val intent = Intent (context, FutsaldetailActivity::class.java)
                                .putExtra("futsal", futsal)
                                .putExtra("userdata", userdata)
                        context.startActivity(intent)
                        }
                    }
                }
                catch (e: Exception){
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                                context,
                                "Error: ${e.toString()}", Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            }

        }
    }

    override fun getItemCount(): Int {
        return futsallist.size
    }
}