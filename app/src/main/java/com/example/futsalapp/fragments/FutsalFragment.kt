package com.example.futsalapp.fragments

import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.futsalapp.MainActivity
import com.example.futsalapp.R
import com.example.futsalapp.adapter.FutsalAdapter
import com.example.futsalapp.db.FutsalDB
import com.example.futsalapp.repository.FutsalRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main


class FutsalFragment : Fragment(R.layout.fragment_futsal) {

    private lateinit var recView: RecyclerView
    private lateinit var linlay: LinearLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


       val view = inflater.inflate(R.layout.fragment_futsal, container, false)

       recView = view.findViewById(R.id.recView)
       linlay = view.findViewById(R.id.linlay)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val futsalrepo = FutsalRepository()
                val futsallist = futsalrepo.getAllFutsal(requireContext())
                withContext(Main) {
                    val futsalAdapter = FutsalAdapter(requireContext(), futsallist)
//                    recView.adapter = futsalAdapter
//                    recView.layoutManager = GridLayoutManager(activity as Context, 2)
                    val manager = GridLayoutManager(context, 2)
                    recView.layoutManager = manager
                    recView.setHasFixedSize(true)
                    recView.adapter = futsalAdapter

                }
            }
            catch (e: Exception){
                withContext(Main) {
                    Toast.makeText(context, "Error : $e", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return view

    }
}