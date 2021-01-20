package com.example.futsalapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.futsalapp.MainActivity
import com.example.futsalapp.R
import com.example.futsalapp.adapter.FutsalAdapter


class FutsalFragment : Fragment() {

    private lateinit var recView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        
       val view = inflater.inflate(R.layout.fragment_futsal, container, false)
       recView = view.findViewById(R.id.recView) 
       val futsalAdapter = FutsalAdapter( this.activity as Context, MainActivity.futsallist)
        recView.adapter = futsalAdapter
        recView.layoutManager = GridLayoutManager(this.activity as Context, 2)
       return view
    }

 
}