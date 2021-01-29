package com.example.futsalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.futsalapp.db.UserDB
import com.example.futsalapp.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignupActivity : AppCompatActivity() {
    private lateinit var rlImg: RelativeLayout
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
        rlImg = findViewById(R.id.rlImg)
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
        CoroutineScope(Dispatchers.IO).launch {
            val user = User(fname, lname, uname, password,address, email, phone)
            UserDB.getInstance(this@SignupActivity)
                .getUserDao()
                .registerUser(user)
            withContext(Main){
                Toast.makeText(this@SignupActivity, "User Registered", Toast.LENGTH_SHORT).show()
            }
        }
    }
}