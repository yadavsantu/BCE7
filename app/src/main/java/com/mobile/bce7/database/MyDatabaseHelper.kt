package database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "UserDB.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS UserData(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT,"+
                    "address TEXT,"+
                    "email TEXT,"+
                    "gender TEXT,"+
                    "country TEXT,"+
                    "terms TEXT)"
        )

    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS UserData")
        onCreate(db)
    }
    fun insertUserData(name: String, address: String, email: String, gender: String, country: String, terms: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("name", name)
            put("address", address)
            put("email", email)
            put("gender", gender)
            put("country", country)
            put("terms", terms)
        }
        db.insert("UserData", null, values)
        db.close()
    }
    fun getAllUserData(): Cursor {
        val db = readableDatabase
        return db.rawQuery("SELECT * FROM UserData", null)
    }
}