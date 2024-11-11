package com.example.test.manager

import android.content.ContentValues
import android.content.Context
import android.util.Log
import androidx.room.Room
import kotlinx.coroutines.coroutineScope

class DBRoom(appContext: Context) {

    val db = Room.databaseBuilder(
        appContext,
        AppDatabase::class.java, "UsersDataBase"
    ).fallbackToDestructiveMigration()
        .build().userDao()

    suspend fun checkUserName(userName: String): Boolean{
        return db.getUsers().run {
            indexOfFirst {
                it.userName == userName
            } != -1
        }
    }

    suspend fun checkEmail(email: String): Boolean{
        return db.getUsers().run {
            indexOfFirst {
                it.email == email
            } != -1
        }
    }

    suspend fun getMaxEnterUser(): User{
        return db.getUsers().maxBy { user -> user.countEnter }
    }

    suspend fun checkPassword(userName: String, password: String): Boolean{
        db.getUsers().run {
            indexOfFirst {
                it.userName == userName && it.password == password
            }.let {
                if (it == -1)
                    return false
                Log.d("Room DataBase",this[it].toString())
                this[it].countEnter++
                db.updateUser(this[it].copy())

                Log.d("Room DataBase", "Password check successful, enter count will update")
                return true
            }
        }
    }
}