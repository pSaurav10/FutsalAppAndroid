package com.example.futsalapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.futsalapp.db.UserDB
import com.example.futsalapp.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginActivity : AppCompatActivity() {
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvSignup: TextView
    private lateinit var cbLogin: CheckBox
    private var isCheck = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        etUsername = findViewById(R.id.etUsername)
        etPassword= findViewById(R.id.etPassword)
        tvSignup = findViewById(R.id.tvSignup)
        btnLogin = findViewById(R.id.btnLogin)
        cbLogin = findViewById(R.id.cbLogin)


        tvSignup.setOnClickListener {
            val intent = Intent(
                    this,
                    SignupActivity::class.java
            )
            startActivity(intent)
            finish()
        }
        btnLogin.setOnClickListener{
           login()
        }
    }
    private fun login() {
        val username = etUsername.text.toString()
        val password = etPassword.text.toString()
        var user : User? = null
        CoroutineScope(Dispatchers.IO).launch {
            user = UserDB
                .getInstance(this@LoginActivity)
                .getUserDao()
                .loginUser(username, password)
            if(user == null) {
                withContext(Main) {
                    Toast.makeText(this@LoginActivity, "Invalid Credentials", Toast.LENGTH_SHORT)
                        .show()
                }
            }
                else{
                if(cbLogin.isChecked){
                    isCheck = true
                    saveSharedpref(username, password)
                }
                    startActivity(Intent(this@LoginActivity,
                            PermissionActivity::class.java))
                finish()
                }
        }
    }
    private fun saveSharedpref(username: String, password: String){
        val sharedPref = getSharedPreferences("FutsalPref", MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("username", username)
        editor.putString("password", password)
        editor.putBoolean("isChecked", isCheck);
        editor.apply()
    }
}
