package com.example.miladoctors

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

lateinit var Cancel_new : Button
lateinit var Save_new : Button
lateinit var name_doc: EditText
lateinit var spec_doc: EditText
lateinit var loc_doc: EditText
lateinit var phone_doc: EditText
lateinit var email_doc: EditText
class New_Doctor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_new_doctor)

        //initialisation Button
        Cancel_new = findViewById(R.id.cancel_new_doc)
        Save_new = findViewById(R.id.save_new_doc)
        //initialisation information of doctor
        name_doc = findViewById(R.id.name_new_doc)
        spec_doc = findViewById(R.id.spec_new_doc)
        loc_doc = findViewById(R.id.loc_new_doc)
        phone_doc = findViewById(R.id.phone_new_doc)
        email_doc = findViewById(R.id.email_new_doc)

        //clic sur le bouton
        Cancel_new.setOnClickListener {
            val myIntent: Intent = Intent(this, List_doctor::class.java)
            startActivity(myIntent)
        }
        Save_new.setOnClickListener {
                if (name_doc.text.toString().isEmpty())
                {
                    Toast.makeText(this, "Please set name of doctor", Toast.LENGTH_SHORT).show()
                }
                else
                {
                    var db = DataBaseHandler(this)
                    db.insertData(name_doc.text.toString(), spec_doc.text.toString(), loc_doc.text.toString(), phone_doc.text.toString(),
                    email_doc.text.toString())
                    val myIntent: Intent = Intent(this, List_doctor::class.java)
                    startActivity(myIntent)
                }
        }
    }
}