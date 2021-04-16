package com.example.futsalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.futsalapp.adapter.CommentAdapter
import com.example.futsalapp.api.ServiceBuilder
import com.example.futsalapp.model.Comment
import com.example.futsalapp.model.Player
import com.example.futsalapp.model.Post
import com.example.futsalapp.repository.PostRepository
import com.example.futsalapp.repository.UserRepository
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostdetailActivity : AppCompatActivity() {
    private lateinit var userimg: CircleImageView
    private lateinit var username: TextView
    private lateinit var date: TextView
    private lateinit var post: TextView
    private lateinit var commentadd: EditText
    private lateinit var btnComment: Button
    private lateinit var commentRec: RecyclerView
    private lateinit var id: String
    private var user: Player? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_postdetail)
        userimg = findViewById(R.id.userimg)
        username = findViewById(R.id.username)
        date = findViewById(R.id.date)
        post = findViewById(R.id.post)
        commentadd = findViewById(R.id.commentadd)
        btnComment = findViewById(R.id.btnComment)
        commentRec = findViewById(R.id.commentRec)

        btnComment.setOnClickListener {
            addComment()
        }

        val posts = intent.getParcelableExtra<Post>("post")
        if (posts != null) {
            id = posts._id
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
                if (response.success == true) {
                    withContext(Main) {
                        val commentadapter =
                            CommentAdapter(this@PostdetailActivity, response.data!!)
                        val manager = LinearLayoutManager(this@PostdetailActivity)
                        commentRec.layoutManager = manager
                        commentRec.adapter = commentadapter
                    }
                }
            } catch (e: Exception) {
                withContext(Main) {
                    Toast.makeText(
                        this@PostdetailActivity,
                        "Error : ${e.toString()}",
                        Toast.LENGTH_SHORT
                    ).show()
                    println(e.toString())
                }
            }
        }
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userrepo = UserRepository()
                val response = userrepo.getUser()
                val success = response.success
                if (success == true) {
                    user = response.data!!
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@PostdetailActivity,
                        "Error: ${e.toString()}", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }


}
    private fun addComment() {
        val postid = id
        val comment = commentadd.text.toString()
        val cusername = user!!.username
        val cuserid = user!!._id
        val cuserimage = user!!.imagepp
        val comments = Comment(comment = comment, cusername = cusername, cuserid = cuserid, cuserimage = cuserimage, id = postid)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val postrepo = PostRepository();
                val response = postrepo.addComment(comments)
                println("Response Success "+response.success)
                if (response.success == true){
                    withContext(Main){
                        Toast.makeText(this@PostdetailActivity, "Comment Added Successfully", Toast.LENGTH_SHORT).show()
                        startActivity(
                            Intent(this@PostdetailActivity, MainActivity::class.java)
                        )
                        finish()
                    }
                }
            }
            catch (ex: Exception){
                withContext(Main){
                    Toast.makeText(this@PostdetailActivity, "$ex", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
