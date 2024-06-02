package com.example.miladoctors

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

lateinit var Add : FloatingActionButton
lateinit var recycler_doc: RecyclerView
var docListAdapter: DoctorListAdapter ?= null
var docList: List<DocListModel> = ArrayList<DocListModel>()
var linearLayoutManager: LinearLayoutManager ?= null

class List_doctor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_doctor)

        //initialisation du button add and recycleView(list of doctors
        Add = findViewById(R.id.add_doctor)
        recycler_doc = findViewById(R.id.recycle_doctors)

        //Show list all of doctor
        fetchlist()

        //Click on Button add new doctor
        Add.setOnClickListener {
            val myIntent: Intent = Intent(this, New_Doctor::class.java)
            startActivity(myIntent)
        }
    }
    private fun fetchlist()
    {
        var db = DataBaseHandler(this)
        docList = db!!.getAllData()
        docListAdapter = DoctorListAdapter(docList,applicationContext)
        linearLayoutManager = LinearLayoutManager(applicationContext)
        recycler_doc.layoutManager = linearLayoutManager
        recycler_doc.adapter = docListAdapter
        docListAdapter?.notifyDataSetChanged()
    }
}