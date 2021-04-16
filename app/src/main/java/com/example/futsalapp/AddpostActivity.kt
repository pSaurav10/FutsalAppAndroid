package com.example.futsalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.futsalapp.fragments.PostFragment
import com.example.futsalapp.model.Player
import com.example.futsalapp.model.Post
import com.example.futsalapp.repository.PostRepository
import com.example.futsalapp.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddpostActivity : AppCompatActivity() {

    private lateinit var etPost: EditText
    private lateinit var btnadd: Button

    private var user: Player? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addpost)
        etPost = findViewById(R.id.etPost)
        btnadd = findViewById(R.id.btnadd)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userrepo = UserRepository()
                val response = userrepo.getUser()
                val success = response.success
                if (success == true){
                    user = response.data!!
                }
            }
            catch (e: Exception){
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@AddpostActivity,
                        "Error: ${e.toString()}", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        btnadd.setOnClickListener {
            addpost()
        }

    }

    private fun addpost() {
        val post = etPost.text.toString()
        val username = user!!.username
        val userimage = user!!.imagepp
        val userid = user!!._id
        val posts = Post(post=post, username=username, userimage = userimage, userId = userid)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val postrepo = PostRepository();
                val response = postrepo.addPost(posts)
                println("Response Success "+response.success)
                if (response.success == true){
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@AddpostActivity, "Post Added Successfully", Toast.LENGTH_SHORT).show()
                        startActivity(
                            Intent(this@AddpostActivity, MainActivity::class.java)
                        )
                        finish()
                    }
                }
            }
            catch (ex: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@AddpostActivity, "$ex", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}