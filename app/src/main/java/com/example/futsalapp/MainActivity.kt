package com.example.futsalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.futsalapp.api.ServiceBuilder
import com.example.futsalapp.fragments.EventFragment
import com.example.futsalapp.fragments.FutsalFragment
import com.example.futsalapp.fragments.PostFragment
import com.example.futsalapp.fragments.UserFragment
import com.example.futsalapp.model.Futsal
import com.example.futsalapp.repository.EventRepository
import com.example.futsalapp.repository.FutsalRepository
import com.example.futsalapp.repository.UserRepository
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {


    private lateinit var bottom_layout: BottomNavigationView
    private lateinit var user_img: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        user_img = findViewById(R.id.user_img)
        bottom_layout = findViewById(R.id.bottom_layout)
        menuitem()
        retrieveFutsal()
        retrieveEvent()


        supportFragmentManager.beginTransaction().apply{
            replace(R.id.frame, FutsalFragment())
            commit()
        }



        }



    private fun menuitem() {
        bottom_layout.setOnNavigationItemSelectedListener { item ->
            when (item.itemId){
                R.id.futsalfrag -> {
                    supportFragmentManager.beginTransaction().apply{
                        replace(R.id.frame, FutsalFragment())
                        commit()
                    }
                    true
                }
                R.id.communityfrag -> {
                    supportFragmentManager.beginTransaction().apply{
                        replace(R.id.frame, PostFragment())
                        commit()
                    }
                    true
                }
                R.id.eventfrag -> {
                    supportFragmentManager.beginTransaction().apply{
                        replace(R.id.frame, EventFragment())
                        commit()
                    }
                    true
                }
                R.id.userfrag -> {
                    supportFragmentManager.beginTransaction().apply{
                        replace(R.id.frame, UserFragment())
                        commit()
                    }
                    true
                }
                else -> false
            }


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

    private fun retrieveEvent(){
        CoroutineScope(Dispatchers.IO).launch{
            try {
                val eventRepo = EventRepository()
                val response = eventRepo.getAllEvent()
                if (response.success == true){
                    val eventlist = response.data
                    eventRepo.insertEvent(this@MainActivity, eventlist!!)
                }

            }
            catch (e:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@MainActivity, "No Internet Connection", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}