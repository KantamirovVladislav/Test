package com.example.test.manager

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBManager (context: Context?): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    val LOG_TAG: String = "DataBaseLog"

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "SoccerSkillsDB.db"
        private const val TABLE_NAME = "User"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "username"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PASSWORD = "password"
        private const val COLUMN_ENTER = "enter"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        Log.d(LOG_TAG, "--- onCreate database ---")
        db?.execSQL("create table $TABLE_NAME ($COLUMN_ID integer primary key autoincrement, "
                +"$COLUMN_NAME text, $COLUMN_EMAIL text, $COLUMN_PASSWORD text, $COLUMN_ENTER integer);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("drop table if exists $TABLE_NAME")
        onCreate(db)
    }

    fun addUser(user: User){
        var cv = ContentValues()
        Log.d(LOG_TAG, "--- insert database ---")
        cv.put("username", user.userName)
        cv.put("email", user.email)
        cv.put("password", user.password)
        cv.put("enter", user.countEnter)
        var rowId: Long = this.writableDatabase.insert("User", null, cv)
        Log.d(LOG_TAG, "Row inserted, ID = $rowId")
    }

    fun clear(){
        this.writableDatabase.delete("User", null, null)
    }

    fun checkUserName(userName: String): Boolean{
        return readUser().run {
            indexOfFirst {
                it.userName == userName
        } != -1
        }
    }

    fun checkEmail(email: String): Boolean{
        return readUser().run {
            indexOfFirst {
                it.email == email
            } != -1
        }
    }

    fun checkPassword(userName: String, password: String): Boolean{
        readUser().run {
            indexOfFirst {
                it.userName == userName && it.password == password
            }.let {
                if (it == -1)
                    return false
                Log.d(LOG_TAG,this[it].toString())
                val updatedEnter = this[it].countEnter + 1
                val contentValues = ContentValues().apply {
                    put(COLUMN_ENTER, updatedEnter)
                }

                val whereClause = "$COLUMN_ID = ?"
                val whereArgs = arrayOf(this[it].index.toString())
                val rowsUpdated = writableDatabase.update(TABLE_NAME, contentValues, whereClause, whereArgs)
                Log.d(LOG_TAG, "Password check successful, enter count will update to $updatedEnter for user: ${this[it].userName}")
                return rowsUpdated > 0

            }
        }
    }


    fun readUser():List<User>{
        Log.d(LOG_TAG, "--- Rows in User: ---")
        var c: Cursor = this.writableDatabase.query("User", null, null, null, null, null, null)
        var users: MutableList<User> = mutableListOf();
        if(c.moveToFirst()){
            val idColIndex: Int = c.getColumnIndex("id")
            val nameColIndex: Int = c.getColumnIndex("username")
            val emailColIndex: Int = c.getColumnIndex("email")
            val passwordColIndex: Int = c.getColumnIndex("password")
            val enterColIndex: Int = c.getColumnIndex("enter")
            do {
                var currentUser = User(
                    c.getInt(idColIndex),
                    c.getString(nameColIndex),
                    c.getString(emailColIndex),
                    c.getString(passwordColIndex),
                    c.getInt(enterColIndex)
                )
                users.add(currentUser)
                Log.d(LOG_TAG, currentUser.toString())
            }
            while (c.moveToNext())
        }
        else{
            Log.d(LOG_TAG, "0 row read")
        }
        c.close()
        return users
    }
}
