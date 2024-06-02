package com.example.miladoctors

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DoctorListAdapter(doctors: List<DocListModel>,val context: Context)
    : RecyclerView.Adapter<DoctorListAdapter.DoctorViewHolder>() {
        private var doctors: List<DocListModel> = ArrayList()

    init {
        this.doctors = doctors
    }
        inner class DoctorViewHolder(view: View): RecyclerView.ViewHolder(view){
            val idDoc: TextView = view.findViewById(R.id.id_doc_card)
            val nameDoc: TextView = view.findViewById(R.id.name_doc_card)
            val specDoc: TextView = view.findViewById(R.id.spec_doc_card)
            val btnShow : ImageButton = view.findViewById(R.id.show_doc_card)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_doc,parent,false)
        return DoctorViewHolder(view)
    }

    override fun getItemCount(): Int {
        return doctors.size
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor = doctors[position]
        holder.idDoc.text = doctor.idDoc.toString()
        holder.nameDoc.text = doctor.nameDoc
        holder.specDoc.text = doctor.specDoc
        holder.btnShow.setOnClickListener { v ->
            val intent = Intent(v.context, Doctor_info::class.java)
            intent.putExtra("ID",holder.idDoc.text.toString())
            v.context.startActivity(intent)
        }
    }
}