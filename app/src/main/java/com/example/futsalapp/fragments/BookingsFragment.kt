package com.example.futsalapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.futsalapp.R
import com.example.futsalapp.adapter.BookingAdapter
import com.example.futsalapp.repository.FutsalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class BookingsFragment : Fragment() {

    private lateinit var recBook: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_bookings, container, false)
        recBook = view.findViewById(R.id.recBook)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val bookrepo = FutsalRepository()
                val response = bookrepo.getAllBooking()
                if (response.success == true) {
                    withContext(Dispatchers.Main) {
                        val bookadapter = BookingAdapter(requireContext(), response.data!!)
                        val manager = LinearLayoutManager(context)
                        recBook.layoutManager = manager
                        recBook.adapter = bookadapter
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Error : $e", Toast.LENGTH_SHORT).show()
                }
            }


        }
        return view
    }
}