package com.example.test

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.test.databinding.ActivityAuthorBinding
import com.example.test.databinding.ActivityRegistrationBinding

class AuthorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthorBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.nextButton.setOnClickListener {
            val intent: Intent = Intent(this@AuthorActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}