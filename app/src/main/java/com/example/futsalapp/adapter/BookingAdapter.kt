package com.example.futsalapp.adapter

import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.futsalapp.R
import com.example.futsalapp.model.FutsalBook
import com.example.futsalapp.repository.FutsalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class BookingAdapter(
    val context: Context,
    val futsalbook: MutableList<FutsalBook>
) : RecyclerView.Adapter<BookingAdapter.BookingViewHolder>() {
    class BookingViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvtime: TextView = view.findViewById(R.id.tvtime)
        val tvDate: TextView = view.findViewById(R.id.tvDate)
        val tvfutsalname: TextView = view.findViewById(R.id.tvfutsalname)
        val tvNumber: TextView = view.findViewById(R.id.tvNumber)
        val btnDelete: ImageButton = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.booking_layout,
            parent,
            false
        )
        return BookingViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        val booking = futsalbook[position]
        holder.tvtime.text = "Time: " + booking.time

        holder.tvfutsalname.text = booking.futsalname+ " booked for"
        holder.tvNumber.text = (position + 1).toString() + ". "

        val inputFormatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
        val outputFormatter: DateTimeFormatter =
            DateTimeFormatter.ofPattern("dd-MM-yyy", Locale.ENGLISH)
        val date: LocalDate = LocalDate.parse(booking.date, inputFormatter)
        val formattedDate: String = outputFormatter.format(date)
        holder.tvDate.text = formattedDate

        holder.btnDelete.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete Booking")
            builder.setMessage("Are you sure you want to delete ${booking.futsalname} booking?")
            builder.setIcon(android.R.drawable.ic_delete)
            builder.setPositiveButton("Yes") { _, _ ->
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val futsalrepo = FutsalRepository()
                        val response = futsalrepo.deleteBookings(booking._id!!)
                        if (response.success == true) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                        context,
                                        "Booking Deleted",
                                        Toast.LENGTH_SHORT
                                ).show()
                            }
                            withContext(Dispatchers.Main) {
                                futsalbook.remove(booking)
                                notifyDataSetChanged()
                            }
                        }
                    } catch (ex: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                    context,
                                    ex.toString(),
                                    Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
            builder.setNegativeButton("No") { _, _ ->
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }



    }

    override fun getItemCount(): Int {
        return futsalbook.size
    }
}