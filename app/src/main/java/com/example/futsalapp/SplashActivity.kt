package com.example.futsalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import com.example.futsalapp.repository.FutsalRepository
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {
    private lateinit var futsal: ImageView
    private lateinit var animationView: LottieAnimationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        futsal = findViewById(R.id.futsal)
        animationView = findViewById(R.id.animationView)

        CoroutineScope(Dispatchers.Main).launch{
          delay(1000)
           futsal.visibility = View.GONE
            animationView.visibility = View.VISIBLE
            delay(2000)
            val sharedPref = getSharedPreferences("FutsalPref", MODE_PRIVATE)
            val isCheck = sharedPref.getBoolean("isChecked", false)
            if (isCheck) {
                startActivity(Intent(this@SplashActivity, PermissionActivity::class.java))
                finish()
            }
            else{
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish()
            }
        }
    }
}