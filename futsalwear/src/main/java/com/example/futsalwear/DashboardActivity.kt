package com.example.futsalwear

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.futsalapp.repository.FutsalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardActivity : AppCompatActivity() {
    private lateinit var tvTitle: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        tvTitle = findViewById(R.id.tvTitle)
        CoroutineScope(Dispatchers.IO).launch{
            try {
                val futsalRepo = FutsalRepository()
                val response = futsalRepo.getAllFutsal()
                println("Success " + response.success)
                if (response.success == true){
                    val futsallist = response.count
                    tvTitle.text = "Total number of futsal is " + futsallist.toString()

                }
            }catch (e:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@DashboardActivity, "No Internet Connection", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}