package com.example.miladoctors

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
lateinit var Cancel_edit : Button
lateinit var Save_edit : Button
lateinit var id_edit_doc: TextView
lateinit var name_edit_doc: EditText
lateinit var spec_edit_doc: EditText
lateinit var loc_edit_doc: EditText
lateinit var phone_edit_doc: EditText
lateinit var email_edit_doc: EditText
class Edit_Doctor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_doctor)

        //initialisation Button
        Cancel_edit = findViewById(R.id.cancel_doc_edit)
        Save_edit = findViewById(R.id.save_doc_edit)
        //initialisation information of doctor
        id_edit_doc = findViewById(R.id.id_edit_doc)
        name_edit_doc = findViewById(R.id.name_edit_doc)
        spec_edit_doc = findViewById(R.id.spec_edit_doc)
        loc_edit_doc = findViewById(R.id.loc_edit_doc)
        phone_edit_doc = findViewById(R.id.phone_edit_doc)
        email_edit_doc = findViewById(R.id.email_edit_doc)
        //Set information of doctor to edit it
        id_edit_doc.text = intent.getStringExtra("ID")
        name_edit_doc.setText(intent.getStringExtra("NAME"))
        spec_edit_doc.setText(intent.getStringExtra("SPEC"))
        loc_edit_doc.setText(intent.getStringExtra("LOC"))
        phone_edit_doc.setText(intent.getStringExtra("PHONE"))
        email_edit_doc.setText(intent.getStringExtra("EMAIL"))
        // Button cancel from edit
        Cancel_edit.setOnClickListener {
            val myIntent: Intent = Intent(this, List_doctor::class.java)
            startActivity(myIntent)
        }
        // Button save to update the new information
        Save_edit.setOnClickListener {
            var db = DataBaseHandler(this)
            db.updateDoctor(Integer.parseInt(id_edit_doc.text.toString()), name_edit_doc.text.toString(),
                spec_edit_doc.text.toString(), loc_edit_doc.text.toString(), phone_edit_doc.text.toString(),
                email_edit_doc.text.toString())
            val myIntent: Intent = Intent(this, List_doctor::class.java)
            startActivity(myIntent)
        }

    }
}