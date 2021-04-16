package com.example.futsalapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.futsalapp.FutsaldetailActivity
import com.example.futsalapp.PostdetailActivity
import com.example.futsalapp.R
import com.example.futsalapp.api.ServiceBuilder
import com.example.futsalapp.model.Post
import com.example.futsalapp.repository.PostRepository
import com.example.futsalapp.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
                val posts: ConstraintLayout = view.findViewById(R.id.posts)
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
        holder.posts.setOnClickListener {
            val intent = Intent (context, PostdetailActivity::class.java)
                .putExtra("post", post)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return postlist.size
    }

}