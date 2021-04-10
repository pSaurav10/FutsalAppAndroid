package com.example.futsalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.futsalapp.db.FutsalDB
import com.example.futsalapp.fragments.EventFragment
import com.example.futsalapp.fragments.FutsalFragment
import com.example.futsalapp.fragments.PostFragment
import com.example.futsalapp.model.Futsal
import com.example.futsalapp.repository.EventRepository
import com.example.futsalapp.repository.FutsalRepository
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private var db:FutsalDB? = null
    private lateinit var bottom_layout: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_layout = findViewById(R.id.bottom_layout)
        retrieveFutsal()
        retrieveEvent()
        val futsalfragment = FutsalFragment()
        val postfragment = PostFragment()
        val eventfragment = EventFragment()
        setCurrentFragment(eventfragment)
        bottom_layout.setOnNavigationItemReselectedListener {
            when (it.itemId){
                R.id.futsal->setCurrentFragment(futsalfragment)
                R.id.community->setCurrentFragment(postfragment)
                R.id.event->setCurrentFragment(eventfragment)
            }

        }


    }
    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.frame, fragment)
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