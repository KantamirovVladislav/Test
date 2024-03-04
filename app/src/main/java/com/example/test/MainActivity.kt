package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var name: EditText
    lateinit var signNow: TextView
    lateinit var signIn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signIn = findViewById(R.id.buttonSignIn)
        name = findViewById(R.id.editTextAccount)
        signNow = findViewById(R.id.textView3)


        signIn.setOnClickListener {
            val intent: Intent = Intent(this@MainActivity, hello::class.java)
            intent.putExtra("userName", name.text.toString())
            startActivity(intent)
        }
        signNow.setOnClickListener {
            val intent: Intent = Intent(this@MainActivity, Registration::class.java)
            startActivity(intent)
        }
    }
}