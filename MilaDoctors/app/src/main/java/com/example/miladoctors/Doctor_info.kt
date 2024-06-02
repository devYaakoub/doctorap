package com.example.miladoctors

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

lateinit var delete : Button
lateinit var edit : Button
lateinit var id : TextView
lateinit var name : TextView
lateinit var spec : TextView
lateinit var loc : TextView
lateinit var phone : TextView
lateinit var email : TextView
lateinit var doctor : DocListModel

class Doctor_info : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_doctor_info)
        //initialisation
        delete = findViewById(R.id.delete_info)
        edit = findViewById(R.id.edit_info)
        id = findViewById(R.id.id_info)
        name = findViewById(R.id.name_info)
        spec = findViewById(R.id.spec_info)
        loc = findViewById(R.id.loc_info)
        phone = findViewById(R.id.phone_info)
        email = findViewById(R.id.email_info)
        var db = DataBaseHandler(this)
        // Get data of selected doctor
        doctor = db?.getDoctor(Integer.parseInt(intent.getStringExtra("ID")))!!
        if (doctor != null) {
            id.text = doctor.idDoc.toString()
            name.text = doctor.nameDoc
            spec.text = doctor.specDoc
            loc.text = doctor.loc_doc
            phone.text = doctor.phone_doc
            email.text = doctor.email_doc
        }
        //clic sur le bouton
        delete.setOnClickListener {
            db?.deleteDoctor(Integer.parseInt(intent.getStringExtra("ID")))
            Toast.makeText(this, "Deleted successful", Toast.LENGTH_SHORT).show()
            val myIntent: Intent = Intent(this, List_doctor::class.java)
            startActivity(myIntent)
        }
        edit.setOnClickListener {
            val myIntent: Intent = Intent(this, Edit_Doctor::class.java)
            myIntent.putExtra("ID", id.text.toString())
            myIntent.putExtra("NAME", name.text.toString())
            myIntent.putExtra("SPEC", spec.text.toString())
            myIntent.putExtra("LOC", loc.text.toString())
            myIntent.putExtra("PHONE", phone.text.toString())
            myIntent.putExtra("EMAIL", email.text.toString())
            startActivity(myIntent)
        }
    }
}