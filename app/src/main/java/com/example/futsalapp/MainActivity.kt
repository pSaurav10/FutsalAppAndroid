package com.example.futsalapp

import android.content.Intent
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.*
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

class MainActivity : AppCompatActivity(), SensorEventListener {


    private lateinit var bottom_layout: BottomNavigationView
    private lateinit var menu: ImageView
    private lateinit var frame: LinearLayout
    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        menu = findViewById(R.id.menu)
        frame = findViewById(R.id.frame)
        bottom_layout = findViewById(R.id.bottom_layout)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        if (!checkSensor())
            return
        else {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }

        menu.setOnClickListener{
            val intent = Intent(this@MainActivity, MapsActivity::class.java)
            startActivity(intent)
        }
        menuitem()

        supportFragmentManager.beginTransaction().apply{
            replace(R.id.frame, FutsalFragment())
            commit()
        }



        }

    private fun checkSensor(): Boolean {
        var flag = true
        if (sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)==null){
            flag = false
        }
        return flag
    }
    override fun onSensorChanged(event: SensorEvent?) {
        val values = event!!.values[0]
        if (values<=4)
        {
            ServiceBuilder.token = null
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
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



}