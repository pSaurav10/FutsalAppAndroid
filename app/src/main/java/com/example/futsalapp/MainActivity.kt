package com.example.futsalapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.futsalapp.fragments.FutsalFragment
import com.example.futsalapp.model.Futsal

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadfutsal()
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.frame, FutsalFragment())
            addToBackStack(null)
            commit()
        }
    }
    private fun loadfutsal(){
       futsallist.add(Futsal("Jadibuti Futsal", "https://i.pinimg.com/564x/f0/ba/81/f0ba812b436a81ccbd00ddc6c97a8a8d.jpg"))
       futsallist.add(Futsal("Jadibuti Futsal", "https://i.pinimg.com/564x/f0/ba/81/f0ba812b436a81ccbd00ddc6c97a8a8d.jpg"))
       futsallist.add(Futsal("Jadibuti Futsal", "https://i.pinimg.com/564x/f0/ba/81/f0ba812b436a81ccbd00ddc6c97a8a8d.jpg"))
       futsallist.add(Futsal("Jadibuti Futsal", "https://i.pinimg.com/564x/f0/ba/81/f0ba812b436a81ccbd00ddc6c97a8a8d.jpg"))
       futsallist.add(Futsal("Jadibuti Futsal", "https://i.pinimg.com/564x/f0/ba/81/f0ba812b436a81ccbd00ddc6c97a8a8d.jpg"))
       futsallist.add(Futsal("Jadibuti Futsal", "https://i.pinimg.com/564x/f0/ba/81/f0ba812b436a81ccbd00ddc6c97a8a8d.jpg"))
       futsallist.add(Futsal("Jadibuti Futsal", "https://i.pinimg.com/564x/f0/ba/81/f0ba812b436a81ccbd00ddc6c97a8a8d.jpg"))
       futsallist.add(Futsal("Jadibuti Futsal", "https://i.pinimg.com/564x/f0/ba/81/f0ba812b436a81ccbd00ddc6c97a8a8d.jpg"))
       futsallist.add(Futsal("Jadibuti Futsal", "https://i.pinimg.com/564x/f0/ba/81/f0ba812b436a81ccbd00ddc6c97a8a8d.jpg"))
       futsallist.add(Futsal("Jadibuti Futsal", "https://i.pinimg.com/564x/f0/ba/81/f0ba812b436a81ccbd00ddc6c97a8a8d.jpg"))

    }
    companion object{
        var futsallist = ArrayList<Futsal>()
    }
}