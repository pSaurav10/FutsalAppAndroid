package com.example.futsalapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.futsalapp.R
import com.example.futsalapp.api.ServiceBuilder
import com.example.futsalapp.model.Comment
import de.hdodenhof.circleimageview.CircleImageView

class CommentAdapter(
        val context: Context,
        val commentlist: MutableList<Comment>
): RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    class CommentViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val usercommentpic : CircleImageView = view.findViewById(R.id.usercommentpic)
        val usercomment : TextView = view.findViewById(R.id.usercomment)
        val username : TextView = view.findViewById(R.id.username)
        val date : TextView = view.findViewById(R.id.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_comment_layout, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = commentlist[position]
        println("Adapter Comments data " + comment)
        holder.usercomment.text = comment.comment
        holder.username.text = comment.cusername
        holder.date.text = comment.ccreatedAt
        val imagepath = ServiceBuilder.loadImagePath() + comment.cuserimage
        Glide.with(context)
                .load(imagepath)
                .fitCenter()
                .into(holder.usercommentpic)
        if (commentlist.size == 0){
            holder.usercomment.text = "No comments Available"
        }
    }

    override fun getItemCount(): Int {
    return commentlist.size
    }
}