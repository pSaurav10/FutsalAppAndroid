package com.example.futsalapp

import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.futsalapp.Notification.NotificationChannels
import com.example.futsalapp.model.Player
import com.example.futsalapp.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserupdateActivity : AppCompatActivity() {

    private lateinit var etFirstname: EditText
    private lateinit var etLastname: EditText
    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPhone: EditText
    private lateinit var etAddress: EditText
    private lateinit var etAge: EditText
    private lateinit var btnUpdate: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userupdate)
        etFirstname = findViewById(R.id.etFirstname)
        etLastname = findViewById(R.id.etLastname)
        etUsername = findViewById(R.id.etUsername)
        etEmail = findViewById(R.id.etEmail)
        etPhone = findViewById(R.id.etPhone)
        etAddress = findViewById(R.id.etAddress)
        etAge = findViewById(R.id.etAge)
        btnUpdate = findViewById(R.id.btnUpdate)

        val player = intent.getParcelableExtra<Player>("user")
        if(player!=null){
            etFirstname.setText(player.fname)
            etLastname.setText(player.lname)
            etUsername.setText(player.username)
            etEmail.setText(player.email)
            etPhone.setText(player.phone)
            etAddress.setText(player.address)
            etAge.setText(player.age)
        }

        btnUpdate.setOnClickListener{
            val fname = etFirstname.text.toString()
            val lname = etLastname.text.toString()
            val username = etUsername.text.toString()
            val email = etEmail.text.toString()
            val phone = etPhone.text.toString()
            val address = etAddress.text.toString()
            val age = etAge.text.toString()
            val player = Player(fname= fname, lname= lname, username= username, email= email, phone = phone, address= address
            , age = age)
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val userRepository = UserRepository()
                    val response = userRepository.updateProfile(player)
                    if (response.success == true){
                        withContext(Main){
                            showNotification(username)
                        }
                    }
                }
                catch (e:Exception){
                    withContext(Main){
                        Toast.makeText(this@UserupdateActivity, "$e", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun showNotification(username: String) {
        val notificationManager = NotificationManagerCompat.from(this)
        val activityIntent = Intent(this, FutsaldetailActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, activityIntent, 0)

        val notificationChannels = NotificationChannels(this)
        notificationChannels.createNotificationChannels()

        val notification = NotificationCompat.Builder(this, notificationChannels.CHANNEL_2)
            .setSmallIcon(R.drawable.notification)
            .setContentTitle("Update successfull")
            .setContentText("$username, your profile has been updated")
            .setColor(Color.GREEN)
            .setContentIntent(pendingIntent)
            .build()
        notificationManager.notify(2, notification)
    }
}