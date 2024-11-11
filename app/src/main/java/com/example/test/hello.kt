package com.example.test

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.test.databinding.ActivityHelloBinding
import com.example.test.databinding.ActivityRegistrationBinding
import com.example.test.manager.DBManager
import com.example.test.manager.DBRoom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class hello : AppCompatActivity() {
    private lateinit var binding: ActivityHelloBinding
    val APP_SETTINGS: String = "app_settings"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelloBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val dbManager = DBRoom(this)


        lifecycleScope.launch {
            val sharedPref = getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE)
            var editor : SharedPreferences.Editor = sharedPref.edit()
            editor.putString("maxEnterName", dbManager.getMaxEnterUser().userName)
            editor.apply()

            var maxEnterName: String = sharedPref.getString("maxEnterName", "not found").toString()

            val users = dbManager.db.getUsers()
            var result: String = ""
            users.forEach{
                    user -> result += "${user.userName} - ${user.countEnter} \n"
            }
            result += "Max enter: $maxEnterName"
            binding.textViewName.text = result;
        }


    }
}