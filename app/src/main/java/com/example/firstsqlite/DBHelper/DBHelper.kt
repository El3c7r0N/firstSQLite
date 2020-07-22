package com.example.firstsqlite.DBHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.firstsqlite.model.BnAdam

class DBHelper(context:Context):SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {


    companion object{
        private val DATABASE_VER = 1
        private val DATABASE_NAME = "EDMTB.db"

        //table

        private val TABLE_NAME = "BnADAM"
        private val COL_ID = "Id"
        private val COL_NAME = "name"
        private val COL_EMAIL = "email"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY = ("CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY, $COL_NAME TEXT,$COL_EMAIL TEXT)")
        db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    val midden:List<BnAdam>
        get() {
            val lowelin = ArrayList<BnAdam>()
            val selectQuery = "SELECT * FROM $TABLE_NAME"
            val db = this.writableDatabase
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor.moveToFirst())
            {
                do {
                    val bnadem = BnAdam()
                    bnadem.id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                    bnadem.name = cursor.getString(cursor.getColumnIndex(COL_NAME))
                    bnadem.email = cursor.getString(cursor.getColumnIndex(COL_EMAIL))

                    lowelin.add(bnadem)
                }while (cursor.moveToNext())
            }
            return lowelin

        }

    fun addBnadem(bnadem:BnAdam)
    {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID, bnadem.id)
        values.put(COL_NAME, bnadem.name)
        values.put(COL_EMAIL, bnadem.email)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun updateBnadem(bnadem:BnAdam):Int
    {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID, bnadem.id)
        values.put(COL_NAME, bnadem.name)
        values.put(COL_EMAIL, bnadem.email)

        return db.update(TABLE_NAME, values, "$COL_ID=?", arrayOf(bnadem.id.toString()))
    }

    fun deleteBnadem(bnadem:BnAdam)
    {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "$COL_ID=?", arrayOf(bnadem.id.toString()))
        db.close()
    }
}