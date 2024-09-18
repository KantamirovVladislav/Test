package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.test.databinding.ActivityMainBinding
import com.example.test.manager.DBManager
import com.example.test.manager.DBRoom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val dbManager = DBRoom(this)

        binding.buttonSignIn.setOnClickListener {

            val userNameValid: String = binding.editTextAccountAuthorization.text.toString().trim()
            val passwordValid: String = binding.editTextPassword.text.toString().trim()
            try {
                lifecycleScope.launch {
                    if (userNameValid.isEmpty())
                        Toast.makeText(applicationContext, getString(R.string.login_not_fill), Toast.LENGTH_SHORT).show()
                    else if (userNameValid.length < 5)
                        Toast.makeText(applicationContext, getString(R.string.login_less_5), Toast.LENGTH_SHORT).show()
                    else if (passwordValid.isEmpty())
                        Toast.makeText(applicationContext, getString(R.string.password_not_fill), Toast.LENGTH_SHORT).show()
                    else if (passwordValid.length < 8)
                        Toast.makeText(applicationContext,getString(R.string.password_less_8), Toast.LENGTH_SHORT).show()
                    else if (!dbManager.checkPassword(userNameValid, passwordValid))
                        Toast.makeText(applicationContext,getString(R.string.invalid_username_or_password), Toast.LENGTH_SHORT).show()
                    else{
                        val intent: Intent = Intent(this@MainActivity, hello::class.java)
                        intent.putExtra("userName", binding.editTextAccountAuthorization.text.toString())
                        startActivity(intent)
                    }
                }
            }catch (ex: Exception){
                Toast.makeText(applicationContext, "${ex.message}", Toast.LENGTH_SHORT).show()
            }


        }
        binding.textView3.setOnClickListener {
            val intent: Intent = Intent(this@MainActivity, Registration::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }
}