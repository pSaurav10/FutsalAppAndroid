package com.example.futsalapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.futsalapp.R
import com.example.futsalapp.api.ServiceBuilder
import com.example.futsalapp.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UserDetailFragment : Fragment() {

    private lateinit var tvname: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvPhone: TextView
    private lateinit var tvDOB: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_user_detail, container, false)
        tvname = view.findViewById(R.id.tvname)
        tvEmail = view.findViewById(R.id.tvEmail)
        tvPhone = view.findViewById(R.id.tvPhone)
        tvDOB = view.findViewById(R.id.tvDOB)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userrepo = UserRepository()
                val response = userrepo.getUser()
                val success = response.success
                if (success == true){
                    val userdata = response.data!!
                    withContext(Dispatchers.Main){
                        tvname.text = userdata.fname +" "+ userdata.lname
                        tvEmail.text = userdata.email
                        tvPhone.text = userdata.phone
                        tvDOB.text = userdata.dob

                    }
                }
            }
            catch (e: Exception){
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        "Error: ${e.toString()}", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        return view
    }


}