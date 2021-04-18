package com.example.futsalapp

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import com.example.futsalapp.api.ServiceBuilder
import com.example.futsalapp.repository.FutsalRepository
import com.example.futsalapp.repository.UserRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {
    private lateinit var futsal: ImageView
    private lateinit var animationView: LottieAnimationView

    var username: String? = null
    var password: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        futsal = findViewById(R.id.futsal)
        animationView = findViewById(R.id.animationView)

        if (!checkInternetConnection()) {
            Toast.makeText(
                    this,
                    "No Internet connection , please switch on the wifi or mobile data",
                    Toast.LENGTH_SHORT
            ).show()
        } else {
            CoroutineScope(Dispatchers.Main).launch {
                delay(1000)
                futsal.visibility = View.GONE
                animationView.visibility = View.VISIBLE
                delay(2000)
                getSharedPref()
                if (username != "") {
                    login()
                } else {
                    loadLoginPage()
                }
            }
        }
    }

    private fun checkInternetConnection(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    private fun login() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = UserRepository()
                val response = repository.userLogin(username!!, password!!)
                if (response.success == true) {
                    ServiceBuilder.token = "Bearer " + response.token

//
                    startActivity(
                            Intent(
                                    this@SplashActivity,
                                    PermissionActivity::class.java
                            )
                    )
                    finish()
                } else {
                    withContext(Dispatchers.Main) {
                        loadLoginPage()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@SplashActivity, "$e", Toast.LENGTH_LONG).show()
                }
            }

        }

    }
    private fun getSharedPref() {
        val sharedPref = getSharedPreferences("FutsalPref", MODE_PRIVATE)
        username = sharedPref.getString("username", "")
        password = sharedPref.getString("password", "")
    }


    private fun loadLoginPage() {
        startActivity(
                Intent(
                        this@SplashActivity,
                        LoginActivity::class.java
                )
        )
        finish()
    }
}