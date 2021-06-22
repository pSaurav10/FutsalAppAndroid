package com.example.futsalapp

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.futsalapp.api.ServiceBuilder
import com.example.futsalapp.model.Player
import com.example.futsalapp.repository.UserRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvSignup: TextView
    private lateinit var cbLogin: CheckBox
    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor?= null
    private var isCheck = false
    private  var player: String? = null
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
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        if (!checkSensor())
            return
        else {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }


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
                    if(cbLogin.isChecked){
                        isCheck = true
                        saveSharedpref(username, password)
                    }
//                    player = response.data
                    startActivity(
                        Intent(
                            this@LoginActivity,
                            PermissionActivity::class.java
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

    private fun saveSharedpref(username: String, password: String){
        val sharedPref = getSharedPreferences("FutsalPref", MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("username", username)
        editor.putString("password", password)
        editor.putBoolean("isChecked", isCheck);
        editor.apply()
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
        if (values>40)
        {
            linearlayout.setBackgroundColor(Color.WHITE)
        }
        if (values<40){
            linearlayout.setBackgroundColor(Color.GRAY)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}
