package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class hello : AppCompatActivity() {
    private lateinit var name: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello)

        name = findViewById(R.id.textViewName)

        name.text = "${getString(R.string.Hello)}, " + intent.getStringExtra("userName") + "!"
    }
}