package com.example.futsalapp

import android.app.DatePickerDialog
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bumptech.glide.Glide
import com.example.futsalapp.Notification.NotificationChannels
import com.example.futsalapp.api.ServiceBuilder
import com.example.futsalapp.model.Futsal
import com.example.futsalapp.model.FutsalBook
import com.example.futsalapp.model.Player
import com.example.futsalapp.repository.FutsalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class FutsaldetailActivity : AppCompatActivity() {

    private lateinit var name: TextView
    private lateinit var address: TextView
    private lateinit var phone: TextView
    private lateinit var description: TextView
    private lateinit var grounds: TextView
    private lateinit var fee: TextView
    private lateinit var futsalimage: ImageView
    private lateinit var spinnerTime: Spinner
    private lateinit var etUsername: EditText
    private lateinit var etDate: Button
    private lateinit var btnBook: Button
    private lateinit var btnPay: Button

    private val time = arrayOf(
           "Select a time", "06 am", "08 am", "09 am", "3 pm", "5 pm"
    )

    private var selectedtime = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_futsaldetail)

        name = findViewById(R.id.name)
        address = findViewById(R.id.address)
        phone = findViewById(R.id.phone)
        description = findViewById(R.id.description)
        grounds = findViewById(R.id.grounds)
        fee = findViewById(R.id.fee)
        futsalimage = findViewById(R.id.futsalimage)
        spinnerTime = findViewById(R.id.spinnerTime)
        etUsername = findViewById(R.id.etUsername)
        etDate = findViewById(R.id.etDate)
        btnBook = findViewById(R.id.btnBook)

        btnBook.setOnClickListener {
            book()
        }


        etDate.setOnClickListener{
            val c = Calendar.getInstance()
            val year1 = c.get(Calendar.YEAR)
            val month1 = c.get(Calendar.MONTH)
            val day1 = c.get(Calendar.DATE)
            val datePickerDialog= DatePickerDialog(
                    this, DatePickerDialog.OnDateSetListener { view, year, month, day ->
                etDate.setText("$year/$month/$day")

            },
                    year1,
                    month1,
                    day1

            )
            datePickerDialog.show()
        }

        val timeadapter = ArrayAdapter<String>(this,
        android.R.layout.simple_list_item_1,time)

        spinnerTime.adapter = timeadapter

        spinnerTime.onItemSelectedListener =
                object: AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long,
                    )
                    {
                        selectedtime = parent?.getItemAtPosition(position).toString()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }
                }


        val futsal = intent.getParcelableExtra<Futsal>("futsal")
        if (futsal !=null) {
            val imagepath = ServiceBuilder.loadImagePath() + futsal.image
            name.setText(futsal.name.toString())
            address.setText("Address: "+futsal.address.toString())
            phone.setText("Phone Number: "+futsal.phoneNumber.toString())
            description.setText(futsal.description.toString())
            grounds.setText("Grounds: "+futsal.grounds.toString())
            fee.setText("Fee: "+futsal.fee.toString())

            Glide.with(this)
                    .load(imagepath)
                    .fitCenter()
                    .into(futsalimage)
        }
    }

    private fun book() {
        val futsal = intent.getParcelableExtra<Futsal>("futsal")
        val userdata = intent.getParcelableExtra< Player>("userdata")
        val username = userdata?.username.toString()
        val date = etDate.text.toString()
        val time = selectedtime
        val futsalname = futsal?.name.toString()
        val futsalid = futsal?._id.toString()
        val userid = userdata?._id.toString()
        val futsalbook = FutsalBook(futsalname=futsalname, futsalid=futsalid,
        date=date, time=time, username=username,userid=userid)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val futsalrepo = FutsalRepository()
                val response = futsalrepo.bookFutsal(futsalbook)
                if (response.success == true){
                    withContext(Main){
                        showNotification(time, futsalname, date)

                    }
                }
            }
            catch (e:Exception){
                withContext(Main){
                    Toast.makeText(this@FutsaldetailActivity, "$e.toString()", Toast.LENGTH_SHORT).show()
                }
                
            }
        }
    }

    private fun showNotification(time: String, futsalname: String, date: String) {
        val notificationManager = NotificationManagerCompat.from(this)
        val activityIntent = Intent(this,FutsaldetailActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, activityIntent, 0)

        val notificationChannels = NotificationChannels(this)
        notificationChannels.createNotificationChannels()

        val notification = NotificationCompat.Builder(this, notificationChannels.CHANNEL_1)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Booking successfull")
                .setContentText("Successfully booked $futsalname for $time at $date")
                .setColor(Color.GREEN)
                .setContentIntent(pendingIntent)
                .build()
        notificationManager.notify(1, notification)
    }
}