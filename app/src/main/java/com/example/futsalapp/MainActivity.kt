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
       futsallist.add(Futsal("Jadibuti Futsal", "https://preview.redd.it/aho7va892mr41.jpg?width=1900&format=pjpg&auto=webp&s=4f3c10b5283bd72b3fa85e61f0c02f8ab536723f"))
       futsallist.add(Futsal("Jadibuti Futsal", "https://preview.redd.it/aho7va892mr41.jpg?width=1900&format=pjpg&auto=webp&s=4f3c10b5283bd72b3fa85e61f0c02f8ab536723f"))
       futsallist.add(Futsal("Jadibuti Futsal", "https://preview.redd.it/aho7va892mr41.jpg?width=1900&format=pjpg&auto=webp&s=4f3c10b5283bd72b3fa85e61f0c02f8ab536723f"))
       futsallist.add(Futsal("Jadibuti Futsal", "https://preview.redd.it/aho7va892mr41.jpg?width=1900&format=pjpg&auto=webp&s=4f3c10b5283bd72b3fa85e61f0c02f8ab536723f"))
       futsallist.add(Futsal("Jadibuti Futsal", "https://preview.redd.it/aho7va892mr41.jpg?width=1900&format=pjpg&auto=webp&s=4f3c10b5283bd72b3fa85e61f0c02f8ab536723f"))
       futsallist.add(Futsal("Jadibuti Futsal", "https://preview.redd.it/aho7va892mr41.jpg?width=1900&format=pjpg&auto=webp&s=4f3c10b5283bd72b3fa85e61f0c02f8ab536723f"))
       futsallist.add(Futsal("Jadibuti Futsal", "https://preview.redd.it/aho7va892mr41.jpg?width=1900&format=pjpg&auto=webp&s=4f3c10b5283bd72b3fa85e61f0c02f8ab536723f"))
       futsallist.add(Futsal("Jadibuti Futsal", "https://preview.redd.it/aho7va892mr41.jpg?width=1900&format=pjpg&auto=webp&s=4f3c10b5283bd72b3fa85e61f0c02f8ab536723f"))


    }
    companion object{
        var futsallist = ArrayList<Futsal>()
    }
}