package com.example.futsalapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.futsalapp.AddpostActivity
import com.example.futsalapp.FutsaldetailActivity
import com.example.futsalapp.R
import com.example.futsalapp.adapter.PostAdapter
import com.example.futsalapp.repository.PostRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PostFragment : Fragment() {

    private lateinit var recView: RecyclerView
    private lateinit var addPost: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_post, container, false)
        recView = view.findViewById(R.id.recView)
        addPost = view.findViewById(R.id.addPost)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val postrepo = PostRepository()
                val response = postrepo.getAllPost()
                if (response.success==true) {
                    withContext(Main) {
                        val postadapter = PostAdapter(requireContext(), response.data!!)
                        val manager = LinearLayoutManager(context)
                        recView.layoutManager = manager
                        recView.adapter = postadapter
                    }
                }
            }
            catch(e: Exception){
                withContext(Main) {
                    Toast.makeText(context, "Error : $e", Toast.LENGTH_SHORT).show()
                }
            }
        }
        
        addPost.setOnClickListener{
            val intent = Intent (context, AddpostActivity::class.java)
            startActivity(intent)
        }
        return view
    }

}