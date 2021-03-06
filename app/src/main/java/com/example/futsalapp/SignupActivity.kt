package com.example.futsalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.futsalapp.model.Player
import com.example.futsalapp.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class SignupActivity : AppCompatActivity() {

    private lateinit var fName: EditText
    private lateinit var lName: EditText
    private lateinit var uName: EditText
    private lateinit var password: EditText
    private lateinit var address: EditText
    private lateinit var email: EditText
    private lateinit var phone: EditText
    private lateinit var btnSignup: Button
    private lateinit var tvLogin: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        fName = findViewById(R.id.fName)
        lName = findViewById(R.id.lName)
        uName = findViewById(R.id.uName)
        address = findViewById(R.id.address)
        email = findViewById(R.id.email)
        phone = findViewById(R.id.phone)
        btnSignup = findViewById(R.id.btnSignup)
        password = findViewById(R.id.pass)
        tvLogin = findViewById(R.id.tvLogin)



        tvLogin.setOnClickListener{
            loadLogin()
        }
        btnSignup.setOnClickListener{
            signUp()
        }
    }

    fun loadLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun signUp(){
        val fname = fName.text.toString()
        val lname = lName.text.toString()
        val uname = uName.text.toString()
        val password = password.text.toString()
        val address = address.text.toString()
        val email = email.text.toString()
        val phone = phone.text.toString()
        val player= Player(fname = fname, lname = lname, username = uname, password = password, address = address, phone = phone, email = email)
        CoroutineScope(Dispatchers.IO).launch {
           try {
               val userRepository = UserRepository();
               val response = userRepository.userRegister(player)
               if (response.success == true){
                   withContext(Main){
                       Toast.makeText(this@SignupActivity, "Register Successfully", Toast.LENGTH_SHORT).show()
                       startActivity(
                               Intent(this@SignupActivity, LoginActivity::class.java)
                       )
                               finish()
                   }
               }
           }
           catch (ex: Exception){
               withContext(Main){
                   Toast.makeText(this@SignupActivity, "$ex", Toast.LENGTH_SHORT).show()
               }
            }
        }
    }
}