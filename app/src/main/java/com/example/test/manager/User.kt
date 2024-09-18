package com.example.test.manager

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val index: Int,
    @ColumnInfo val userName: String,
    @ColumnInfo val email: String,
    @ColumnInfo val password: String,
    @ColumnInfo var countEnter: Int
) {
    override fun toString(): String {
        return "$index $userName $email $password $countEnter \n"
    }
}