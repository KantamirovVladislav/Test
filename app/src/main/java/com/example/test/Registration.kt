package com.example.test

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Registration : AppCompatActivity() {

    private lateinit var userName: EditText
    private lateinit var email:EditText
    private lateinit var password: EditText
    private lateinit var repeatPassword: EditText
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        userName = findViewById(R.id.editTextAccount)
        email = findViewById(R.id.editTextEmail)
        password = findViewById(R.id.editTextPassword)
        repeatPassword = findViewById(R.id.editTextRepeatPassword)
    }

    fun clickCreateAccount(v: View){
        if (userName.text.toString().trim().isEmpty()) Toast.makeText(applicationContext, "Поле логина не заполнено" , Toast.LENGTH_SHORT).show()
        else if (email.text.toString().trim().isEmpty()) Toast.makeText(applicationContext, "Поле почтового адреса не заполнено", Toast.LENGTH_SHORT).show()
        else if (password.text.toString().trim().isEmpty()) Toast.makeText(applicationContext, "Поле пароля не введено", Toast.LENGTH_SHORT).show()
        else if (repeatPassword.text.toString().trim().isEmpty()) Toast.makeText(applicationContext, "Поле пароля (повторное) не введено", Toast.LENGTH_SHORT).show()
        else if (password.text.toString().trim() != repeatPassword.text.toString().trim()) Toast.makeText(applicationContext, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
        else {
            val intent: Intent = Intent(this, hello::class.java)
            intent.putExtra("userName", userName.text.toString())
            startActivity(intent)
        }
    }
}