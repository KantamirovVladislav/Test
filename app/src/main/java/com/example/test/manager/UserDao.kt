package com.example.test.manager

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Insert
    suspend fun insertUsers(vararg users: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Query("select * from user")
    suspend fun getUsers(): List<User>

    @Query("select * from user where userName == (:inputUserName)")
    suspend fun getUserByUserName(inputUserName: String): User

}