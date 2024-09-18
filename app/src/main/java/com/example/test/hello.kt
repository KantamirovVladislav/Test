package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.test.databinding.ActivityHelloBinding
import com.example.test.databinding.ActivityRegistrationBinding
import com.example.test.manager.DBManager
import com.example.test.manager.DBRoom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class hello : AppCompatActivity() {
    private lateinit var binding: ActivityHelloBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelloBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val dbManager = DBRoom(this)

        CoroutineScope(Dispatchers.IO).launch {
            val users = dbManager.db.getUsers()
            var result: String = ""
            users.forEach{
                    user -> result += "${user.userName} - ${user.countEnter} \n"
            }
            binding.textViewName.text = result;
        }


    }
}