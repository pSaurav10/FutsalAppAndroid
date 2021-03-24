package com.example.futsalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.futsalapp.db.FutsalDB
import com.example.futsalapp.fragments.FutsalFragment
import com.example.futsalapp.model.Futsal
import com.example.futsalapp.repository.FutsalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private var db:FutsalDB? = null
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)

        initialize()
        retrieveFutsal()
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.frame, FutsalFragment())
            addToBackStack(null)
            commit()
        }
    }


    private fun retrieveFutsal() {
        CoroutineScope(Dispatchers.IO).launch{
            try {
                val futsalRepo = FutsalRepository()
                val response = futsalRepo.getAllFutsal()
                if (response.success == true){
                    val futsallist = response.data
                    futsalRepo.insertFutsal(this@MainActivity, futsallist!!)

                }
            }catch (e:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@MainActivity, "No Internet Connection", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initialize() {
        db = FutsalDB.getInstance(application)
    }
}