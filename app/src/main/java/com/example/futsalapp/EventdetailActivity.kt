package com.example.futsalapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.bumptech.glide.Glide
import com.example.futsalapp.api.ServiceBuilder
import com.example.futsalapp.model.Event

class EventdetailActivity : AppCompatActivity() {

    private lateinit var eventimage: ImageView
    private lateinit var tvname: TextView
    private lateinit var tvdescription: TextView
    private lateinit var tvdate: TextView
    private lateinit var tvfee: TextView
    private lateinit var tvphone: TextView
    private lateinit var tvlocation: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eventdetail)

        eventimage = findViewById(R.id.eventimage)
        tvname = findViewById(R.id.tvname)
        tvdescription = findViewById(R.id.tvdescription)
        tvdate = findViewById(R.id.tvdate)
        tvfee = findViewById(R.id.tvfee)
        tvphone = findViewById(R.id.tvphone)
        tvlocation = findViewById(R.id.tvlocation)


        val event = intent.getParcelableExtra<Event>("event")
        if (event != null){
            tvname.text = event.name
            tvdescription.text = event.description
            tvphone.text = event.phone
            tvlocation.text = event.location
            tvdate.text = event.date
            tvfee.text = event.fee
            val imagepath = ServiceBuilder.loadImagePath() + event.image
            Glide.with(this)
                .load(imagepath)
                .fitCenter()
                .into(eventimage)
        }

    }
}