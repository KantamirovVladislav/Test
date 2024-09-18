package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.test.manager.DBManager
import com.example.test.manager.DBRoom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class hello : AppCompatActivity() {
    private lateinit var name: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello)

        name = findViewById(R.id.textViewName)

        val dbManager = DBRoom(this)

        CoroutineScope(Dispatchers.IO).launch {
            val users = dbManager.db.getUsers()
            var result: String = ""
            users.forEach{
                    user -> result += "${user.userName} - ${user.countEnter} \n"
            }
            name.text = result;
        }


    }
}