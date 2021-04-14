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
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UserFragment : Fragment() {

    private lateinit var userimage: CircleImageView
    private lateinit var tv_name: TextView
    private lateinit var tv_address: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view =  inflater.inflate(R.layout.fragment_user, container, false)
        userimage = view.findViewById(R.id.userimage)
        tv_name = view.findViewById(R.id.tv_name)
        tv_address = view.findViewById(R.id.tv_address)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userrepo = UserRepository()
                val response = userrepo.getUser()
                val success = response.success
                if (success == true){
                    val userdata = response.data!!
                    val imagepath = ServiceBuilder.loadImagePath() + userdata.imagepp
                    withContext(Dispatchers.Main){
                        tv_name.text = userdata.fname +" "+ userdata.lname
                        tv_address.text = userdata.address
                        Glide.with(requireContext())
                            .load(imagepath)
                            .fitCenter()
                            .into(userimage)
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