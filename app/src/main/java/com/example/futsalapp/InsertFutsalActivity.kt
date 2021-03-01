package com.example.futsalapp

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.*
import com.example.futsalapp.R
import com.example.futsalapp.model.Futsal
import com.example.futsalapp.repository.FutsalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class InsertFutsalActivity : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var address: EditText
    private lateinit var number: EditText
    private lateinit var description: EditText
    private lateinit var photo: ImageView
    private lateinit var save: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_futsal)
        name = findViewById(R.id.name)
        address = findViewById(R.id.address)
        number = findViewById(R.id.number)
        description = findViewById(R.id.description)
        photo = findViewById(R.id.photo)
        save = findViewById(R.id.save)

        save.setOnClickListener {
//            savefutsal()
        }

        photo.setOnClickListener {
//            loadPopup()
        }

    }

//    private fun loadPopup() {
//        val popupMenu = PopupMenu(this@InsertFutsalActivity, photo)
//        popupMenu.menuInflater.inflate(R.menu.gallery_camera,  popupMenu.menu)
//        popupMenu.setOnMenuItemClickListener { item ->
//            when (item.itemId) {
//                R.id.menuCamera ->
//                    openCamera()
//                R.id.menuGallery ->
//                    openGallery()
//            }
//            true
//        }
//        popupMenu.show()
//    }
}