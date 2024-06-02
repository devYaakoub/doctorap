package com.example.miladoctors

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

lateinit var Enter : Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var db = DataBaseHandler(this)
        db!!.writeDataBase()
        //initialisation
        Enter = findViewById(R.id.btn_enter)

        //clic sur le bouton
        Enter.setOnClickListener {
            val myIntent: Intent = Intent(this, List_doctor::class.java)
            startActivity(myIntent)
        }
    }
}