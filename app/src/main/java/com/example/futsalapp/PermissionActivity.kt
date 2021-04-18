package com.example.futsalapp

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.futsalapp.repository.EventRepository
import com.example.futsalapp.repository.FutsalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PermissionActivity : AppCompatActivity() {
    private val permissions = arrayOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.ACCESS_NETWORK_STATE,
    )
    private val REQUEST: Int = 112

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)
        retrieveFutsal()
        retrieveEvent()
        // Check for permission
        if (!hasPermission()) {
            requestPermission()
        }
    }
    private fun retrieveFutsal() {
        CoroutineScope(Dispatchers.IO).launch{
            try {
                val futsalRepo = FutsalRepository()
                val response = futsalRepo.getAllFutsal()
                if (response.success == true){
                    val futsallist = response.data
                    futsalRepo.insertFutsal(this@PermissionActivity, futsallist!!)

                }
            }catch (e:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@PermissionActivity, "No Internet Connection", Toast.LENGTH_SHORT).show()
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
                    eventRepo.insertEvent(this@PermissionActivity, eventlist!!)
                }

            }
            catch (e:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@PermissionActivity, "No Internet Connection", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun requestPermission() {
        ActivityCompat.requestPermissions(
                this@PermissionActivity,
                permissions, 112
        )
    }
    private fun hasPermission(): Boolean {
        for (permission in permissions) {
            if (ActivityCompat.checkSelfPermission(
                            this,
                            permission
                    ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false;
            }
        }
        return true
    }
    private fun callnextActivity(){
        startActivity(Intent(this@PermissionActivity, MainActivity::class.java))
        finish()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        when (requestCode) {
            REQUEST -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callnextActivity()
                } else {
                    Toast.makeText(this, "Grant Access to use the app", Toast.LENGTH_LONG).show()
                    requestPermission()
                }
            }
        }
    }

}