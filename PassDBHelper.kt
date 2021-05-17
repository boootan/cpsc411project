package com.example.easypass

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

val DATABASE_NAME = "MyDB"
val TABLE_NAME = "Entries"
val COL_USER = "user"
val COL_ID = "id"
val COL_PLATFORM = "platform"
val COL_PASS = "pass"

public class PassDBHelper(context: Context):SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + "(" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                COL_USER + " VARCHAR(256)," +
                COL_PLATFORM + " VARCHAR(256)," +
                COL_PASS + " VARCHAR(256))";

        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(entry: passItem) {
        val mydb = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_USER, entry.user)
        cv.put(COL_PLATFORM, entry.platform)
        cv.put(COL_PASS, entry.pass)
        mydb.insert(TABLE_NAME, null, cv)
    }

    fun readData() : MutableList<passItem> {
        var list : MutableList<passItem> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT * FROM " + TABLE_NAME
        val result = db.rawQuery(query, null)
        if(result.moveToFirst()) {
            do {
                var entry = passItem()
                entry.user = result.getString(result.getColumnIndex(COL_USER))
                entry.platform = result.getString(result.getColumnIndex(COL_PLATFORM))
                entry.pass = result.getString(result.getColumnIndex(COL_PASS))
                entry.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                list.add(entry)
            }
                while(result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }

    fun deleteData(position: Int) {
        val mydb = this.writableDatabase
        mydb.delete(TABLE_NAME, "$COL_ID=?", arrayOf(position.toString()))
        mydb.close()
    }
}