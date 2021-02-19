package com.example.futsalapp

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.futsalapp.api.ServiceBuilder
import com.example.futsalapp.repository.UserRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvSignup: TextView
    private lateinit var cbLogin: CheckBox
    private var isCheck = false
    private lateinit var linearlayout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        etUsername = findViewById(R.id.etUsername)
        etPassword= findViewById(R.id.etPassword)
        tvSignup = findViewById(R.id.tvSignup)
        btnLogin = findViewById(R.id.btnLogin)
        cbLogin = findViewById(R.id.cbLogin)
        linearlayout = findViewById(R.id.linearlayout)

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
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = UserRepository()
                val response = repository.userLogin(username, password)
                if (response.success == true){
                    ServiceBuilder.token = "Bearer " + response.token
                    startActivity(
                        Intent(
                            this@LoginActivity,
                            MainActivity::class.java
                        )
                    )
                    finish()
                }
                else {
                    withContext(Dispatchers.Main){
                        val snack = Snackbar.make(
                            linearlayout, "Invalid Credentials", Snackbar.LENGTH_LONG
                        )
                        snack.setAction("OK", View.OnClickListener {
                            snack.dismiss()
                        })
                        snack.show()
                    }
                }
            }
            catch (e: Exception)
            {
                withContext(Dispatchers.Main){
                    Toast.makeText(this@LoginActivity, "$e", Toast.LENGTH_LONG).show()
                }
            }

        }
    }
}
