package com.example.futsalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.futsalapp.db.UserDB
import com.example.futsalapp.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvSignup: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        etUsername = findViewById(R.id.etUsername)
        etPassword= findViewById(R.id.etPassword)
        tvSignup = findViewById(R.id.tvSignup)
        btnLogin = findViewById(R.id.btnLogin)
        tvSignup.setOnClickListener {
            val intent = Intent(
                    this,
                    SignupActivity::class.java
            )
            startActivity(intent)
            finish()
        }
        btnLogin.setOnClickListener{
           login()
        }
    }
    private fun login() {
        val username = etUsername.text.toString()
        val password = etPassword.text.toString()
        var user : User? = null
        CoroutineScope(Dispatchers.IO).launch {
            user = UserDB
                .getInstance(this@LoginActivity)
                .getUserDao()
                .loginUser(username, password)
            if(user == null) {
                withContext(Main) {
                    Toast.makeText(this@LoginActivity, "Invalid Credentials", Toast.LENGTH_SHORT)
                        .show()
                }
            }
                else{
                    startActivity(Intent(this@LoginActivity,
                    MainActivity::class.java))
                finish()
                }
            }
        }
}
