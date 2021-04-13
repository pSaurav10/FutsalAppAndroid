package com.example.futsalapp

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.example.futsalapp.api.ServiceBuilder
import com.example.futsalapp.model.Futsal
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
        val username = etUsername.text.toString()
        val date = etDate.text.toString()
        val time = selectedtime
        val futsalname = futsal?.name.toString()
    }
}