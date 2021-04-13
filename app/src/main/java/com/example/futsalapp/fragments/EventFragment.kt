package com.example.futsalapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.futsalapp.R
import com.example.futsalapp.adapter.EventAdapter
import com.example.futsalapp.adapter.FutsalAdapter
import com.example.futsalapp.repository.EventRepository
import com.example.futsalapp.repository.FutsalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EventFragment : Fragment(R.layout.fragment_event) {
    private lateinit var recView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_event, container, false)

        recView = view.findViewById(R.id.recView)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val eventrepo = EventRepository()
                val eventlist = eventrepo.getAllEvent(requireContext())
                withContext(Dispatchers.Main) {
                    val eventAdapter = EventAdapter(requireContext(), eventlist)
                    val manager = LinearLayoutManager(context)
                    recView.layoutManager = manager
                    recView.setHasFixedSize(true)
                    recView.adapter = eventAdapter

                }
            }
            catch (e: Exception){
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Error : $e", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return view
    }


}