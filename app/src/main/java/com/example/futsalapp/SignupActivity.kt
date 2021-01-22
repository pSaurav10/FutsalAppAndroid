package com.example.futsalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

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
    }
    fun loadLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}