package com.example.test.manager

data class User(val index: Int,val userName: String,val email: String, val password: String, var countEnter: Int) {
    override fun toString(): String {
        return "$index $userName $email $password $countEnter \n"
    }
}