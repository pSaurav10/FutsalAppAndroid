package com.example.futsalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.futsalapp.db.FutsalDB
import com.example.futsalapp.entity.FutsalItem
import com.example.futsalapp.fragments.FutsalFragment
import com.example.futsalapp.model.Futsal
import com.example.futsalapp.repository.FutsalRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private var db:FutsalDB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
        retrieveFutsal()
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.frame, FutsalFragment())
            addToBackStack(null)
            commit()
        }
    }

    private fun saveWith(futsallist: ArrayList<Futsal>){

            val futsals = ArrayList<FutsalItem>()

            for (futsal in futsallist){
                val item = FutsalItem()
                item.name = futsal.name
                item.address = futsal.address
                item.phoneNumber = futsal.phoneNumber
                item.description = futsal.description
                item.image = futsal.image
                futsals.add(item)
            }
        CoroutineScope(Dispatchers.IO).launch{
            db?.getFutsalDao()?.insert(futsals)
        }

    }

    private fun retrieveFutsal() {
        CoroutineScope(Dispatchers.IO).launch{
            try {
                val futsalRepo = FutsalRepository()
                val response = futsalRepo.getAllFutsal()
                if (response.success == true){
                    val futsallist = response.data
                    futsallist?.let {
                        saveWith(it)
                    }
                }
            }catch (e:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@MainActivity, "Error: ${e.toString()}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initialize() {
        db = FutsalDB.getInstance(application)
    }
}