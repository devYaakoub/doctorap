package com.example.miladoctors

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME ="MyDB"
val COL_ID ="ID"
val TABLE_NAME ="DOCTORS"
val COL_NAME ="NAME"
val COL_SPEC ="SPECIALTY"
val COL_LOC ="LOCATION"
val COL_PH ="PHONE"
val COL_EMAIL ="EMAIL"

class DataBaseHandler(var context: Context): SQLiteOpenHelper(context, DATABASE_NAME,null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE "+ TABLE_NAME+"("+
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                COL_NAME + " VARCHAR(256),"+
                COL_SPEC + " VARCHAR(256),"+
                COL_LOC + " VARCHAR(256),"+
                COL_PH + " VARCHAR(256),"+
                COL_EMAIL + " VARCHAR(256));"
        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, v1: Int, v2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun CreateDataBase()
    {
        val db = this.writableDatabase
    }
    fun insertData(name: String,spec: String,loc: String , phone: String , email: String)
    {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME,name)
        cv.put(COL_SPEC,spec)
        cv.put(COL_LOC,loc)
        cv.put(COL_PH,phone)
        cv.put(COL_EMAIL,email)
        var result = db.insert(TABLE_NAME,null,cv)
        if (result == (-1).toLong())
            Toast.makeText(context,"Error to insert Data !!",Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context,"Success insert Data",Toast.LENGTH_SHORT).show()
        db.close()
    }
    fun getAllData(): List<DocListModel> {
        val docList = ArrayList<DocListModel>()

        val db = this.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        if (cursor != null)
        {
            cursor.moveToFirst()
                do {
                    val doctors = DocListModel()
                    doctors.idDoc = cursor.getInt(0)
                    doctors.nameDoc = cursor.getString(1)
                    doctors.specDoc = cursor.getString(2)
                    doctors.loc_doc = cursor.getString(3)
                    doctors.phone_doc = cursor.getString(4)
                    doctors.email_doc = cursor.getString(5)
                    docList.add(doctors)
                }while (cursor.moveToNext())
        }
        cursor.close()
        return docList
    }

    fun getDoctor(id : Int): DocListModel
    {
        val doctor = DocListModel()
        val db = this.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE ID = $id",null)
        cursor?.moveToFirst()
        doctor.idDoc = cursor.getInt(0)
        doctor.nameDoc = cursor.getString(1)
        doctor.specDoc = cursor.getString(2)
        doctor.loc_doc = cursor.getString(3)
        doctor.phone_doc = cursor.getString(4)
        doctor.email_doc = cursor.getString(5)
        cursor.close()
        return doctor
    }

    fun deleteDoctor(id : Int)
    {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "ID = $id",null)
        db.close()
    }

    fun updateDoctor(id: Int,name: String,spec: String,loc: String , phone: String , email: String)
    {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME,name)
        cv.put(COL_SPEC,spec)
        cv.put(COL_LOC,loc)
        cv.put(COL_PH,phone)
        cv.put(COL_EMAIL,email)
        var result = db.update(TABLE_NAME, cv, "ID = $id",null)
        if (result.toLong() == (-1).toLong())
            Toast.makeText(context,"Error to update Data !!",Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context,"Success updating Data",Toast.LENGTH_SHORT).show()
        db.close()
    }

    fun  writeDataBase()
    {
        val db = this.writableDatabase
    }

}