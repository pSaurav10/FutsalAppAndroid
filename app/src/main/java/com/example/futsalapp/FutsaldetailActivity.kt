package com.example.futsalapp

import android.app.Activity
import android.app.DatePickerDialog
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bumptech.glide.Glide
import com.esewa.android.sdk.payment.ESewaConfiguration
import com.esewa.android.sdk.payment.ESewaPayment
import com.esewa.android.sdk.payment.ESewaPaymentActivity
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
    private lateinit var etDate: Button
    private lateinit var btnBook: Button
    private lateinit var btnPay: Button

    private val CONFIG_ENVIRONMENT = ESewaConfiguration.ENVIRONMENT_TEST
    private val REQUEST_CODE_PAYMENT = 1
    private var eSewaConfiguration: ESewaConfiguration? = null

    private val MERCHANT_ID = "JB0BBQ4aD0UqIThFJwAKBgAXEUkEGQUBBAwdOgABHD4DChwUAB0R"
    private val MERCHANT_SECRET_KEY = "BhwIWQQADhIYSxILExMcAgFXFhcOBwAKBgAXEQ=="


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
        etDate = findViewById(R.id.etDate)
        btnBook = findViewById(R.id.btnBook)
        btnPay = findViewById(R.id.btnPay)

        eSewaConfiguration = ESewaConfiguration()
                .clientId(MERCHANT_ID)
                .secretKey(MERCHANT_SECRET_KEY)
                .environment(CONFIG_ENVIRONMENT)


        btnBook.setOnClickListener {
            book()
        }

        btnPay.setOnClickListener {
            esewapay()
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
                android.R.layout.simple_list_item_1, time)

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
            address.setText("Address: " + futsal.address.toString())
            phone.setText("Phone Number: " + futsal.phoneNumber.toString())
            description.setText(futsal.description.toString())
            grounds.setText("Grounds: " + futsal.grounds.toString())
            fee.setText("Fee: " + futsal.fee.toString())

            Glide.with(this)
                    .load(imagepath)
                    .fitCenter()
                    .into(futsalimage)
        }
    }

    private fun esewapay() {
        val eSewaPayment = ESewaPayment("5", "someProductName", "someUniqueId_" + System.nanoTime(), "https://somecallbackurl.com")
        val intent = Intent(this, ESewaPaymentActivity::class.java)
        intent.putExtra(ESewaConfiguration.ESEWA_CONFIGURATION, eSewaConfiguration)
        intent.putExtra(ESewaPayment.ESEWA_PAYMENT, eSewaPayment)
        startActivityForResult(intent, REQUEST_CODE_PAYMENT)

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                val s = data!!.getStringExtra(ESewaPayment.EXTRA_RESULT_MESSAGE)
                Log.i("Proof of Payment", s!!)
                Toast.makeText(this, "SUCCESSFUL PAYMENT", Toast.LENGTH_SHORT).show()
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Canceled By User", Toast.LENGTH_SHORT).show()
            } else if (resultCode == ESewaPayment.RESULT_EXTRAS_INVALID) {
                val s = data!!.getStringExtra(ESewaPayment.EXTRA_RESULT_MESSAGE)
                Log.i("Proof of Payment", s!!)
            }
        }
    }


    private fun book() {
        val futsal = intent.getParcelableExtra<Futsal>("futsal")
        val userdata = intent.getParcelableExtra<Player>("userdata")
        val username = userdata?.username.toString()
        val date = etDate.text.toString()
        val time = selectedtime
        val futsalname = futsal?.name.toString()
        val futsalid = futsal?._id.toString()
        val userid = userdata?._id.toString()
        val futsalbook = FutsalBook(futsalname = futsalname, futsalid = futsalid,
                date = date, time = time, username = username, userid = userid)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val futsalrepo = FutsalRepository()
                val response = futsalrepo.bookFutsal(futsalbook)
                if (response.success == true){
                    withContext(Main){
                        showNotification(time, futsalname, date)
                        btnPay.visibility = View.VISIBLE
                    }
                }
            }
            catch (e: Exception){
                withContext(Main){
                    Toast.makeText(this@FutsaldetailActivity, "$e.toString()", Toast.LENGTH_SHORT).show()
                }
                
            }
        }
    }

    private fun showNotification(time: String, futsalname: String, date: String) {
        val notificationManager = NotificationManagerCompat.from(this)
        val activityIntent = Intent(this, FutsaldetailActivity::class.java)
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