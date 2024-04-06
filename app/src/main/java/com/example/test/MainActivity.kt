package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var name: EditText
    lateinit var password: EditText
    lateinit var signNow: TextView
    lateinit var signIn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signIn = findViewById(R.id.buttonSignIn)
        name = findViewById(R.id.editTextAccountAuthorization)
        password = findViewById(R.id.editTextPassword)
        signNow = findViewById(R.id.textView3)

        signIn.setOnClickListener {

            val userNameValid: String = name.text.toString().trim()
            val passwordValid: String = password.text.toString().trim()

            if (userNameValid.isEmpty())
                Toast.makeText(applicationContext, getString(R.string.login_not_fill), Toast.LENGTH_SHORT).show()
            else if (userNameValid.length < 5)
                Toast.makeText(applicationContext, getString(R.string.login_less_5), Toast.LENGTH_SHORT).show()
            else if (passwordValid.isEmpty())
                Toast.makeText(applicationContext, getString(R.string.password_not_fill), Toast.LENGTH_SHORT).show()
            else if (passwordValid.length < 8)
                Toast.makeText(applicationContext,getString(R.string.password_less_8), Toast.LENGTH_SHORT).show()
            else{
                val intent: Intent = Intent(this@MainActivity, hello::class.java)
                intent.putExtra("userName", name.text.toString())
                startActivity(intent)
            }
        }
        signNow.setOnClickListener {
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