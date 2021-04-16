package com.example.futsalapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.futsalapp.adapter.CommentAdapter
import com.example.futsalapp.adapter.PostAdapter
import com.example.futsalapp.api.ServiceBuilder
import com.example.futsalapp.model.Post
import com.example.futsalapp.repository.PostRepository
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text

class PostdetailActivity : AppCompatActivity() {
    private lateinit var userimg: CircleImageView
    private lateinit var username: TextView
    private lateinit var date: TextView
    private lateinit var post: TextView
    private lateinit var comment_edt: EditText
    private lateinit var commentRec: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_postdetail)
        userimg = findViewById(R.id.userimg)
        username = findViewById(R.id.username)
        date = findViewById(R.id.date)
        post = findViewById(R.id.post)
        comment_edt = findViewById(R.id.comment_edt)
        commentRec = findViewById(R.id.commentRec)

        val posts = intent.getParcelableExtra<Post>("post")
        if (posts !=null) {
            post.text = posts.post.toString()
            username.text = posts.username.toString()
            date.text = posts.createdAt.toString()
            val imagepath = ServiceBuilder.loadImagePath() + posts.userimage
            Glide.with(this)
                    .load(imagepath)
                    .fitCenter()
                    .into(userimg)
        }
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val postrepo = PostRepository()
                val response = postrepo.getAllComment(posts!!._id)
                if (response.success==true) {
                    withContext(Main) {
                        val commentadapter = CommentAdapter(this@PostdetailActivity, response.data!!)
                        val manager = LinearLayoutManager(this@PostdetailActivity)
                        commentRec.layoutManager = manager
                        commentRec.adapter = commentadapter
                    }
                }
            }
            catch(e: Exception){
                withContext(Main) {
                    Toast.makeText(this@PostdetailActivity, "Error : ${e.toString()}", Toast.LENGTH_SHORT).show()
                    println(e.toString())
                }
            }
        }

    }
}