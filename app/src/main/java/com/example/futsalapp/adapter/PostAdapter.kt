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
import com.example.futsalapp.model.Post

class PostAdapter (
        val context: Context,
        val postlist: MutableList<Post>
        ): RecyclerView.Adapter<PostAdapter.PostViewHolder>()
{
            class PostViewHolder(view: View): RecyclerView.ViewHolder(view){
                val tvUsername: TextView =  view.findViewById(R.id.tvUsername)
                val tvDate: TextView =  view.findViewById(R.id.tvDate)
                val tvPost: TextView =  view.findViewById(R.id.tvPost)
                val imgProfile: ImageView = view.findViewById(R.id.imgProfile)
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_layout, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postlist[position]
        holder.tvUsername.text = post.username
        holder.tvDate.text = post.createdAt
        holder.tvPost.text = post.post

        val imagePath = ServiceBuilder.loadImagePath() + post.userimage
        Glide.with(context)
                .load(imagePath)
                .fitCenter()
                .into(holder.imgProfile)
//        Single posts
    }

    override fun getItemCount(): Int {
        return postlist.size
    }

}