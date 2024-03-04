package com.example.test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern

class Registration : AppCompatActivity() {

    private lateinit var userName: EditText
    private lateinit var email:EditText
    private lateinit var password: EditText
    private lateinit var repeatPassword: EditText
    private lateinit var regBtn: Button
    private lateinit var backTextView: TextView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        userName = findViewById(R.id.editTextAccountRegistration)
        email = findViewById(R.id.editTextEmail)
        password = findViewById(R.id.editTextPassword)
        repeatPassword = findViewById(R.id.editTextRepeatPassword)
        regBtn = findViewById(R.id.buttonReg)
        backTextView = findViewById(R.id.textViewBackRegistration)


        regBtn.setOnClickListener{

            val userNameValid: String = userName.text.toString().trim()
            val emailValid: String = email.text.toString().trim()
            val passwordValid: String = password.text.toString().trim()
            val repeatPasswordValid: String = repeatPassword.text.toString().trim()

            if (userNameValid.isEmpty())
                Toast.makeText(applicationContext, "Поле логина не заполнено" , Toast.LENGTH_SHORT).show()
            else if (userNameValid.length < 5)
                Toast.makeText(applicationContext, "Поле логина аккаунта не может быть меньше 5 символов", Toast.LENGTH_SHORT).show()
            else if (emailValid.isEmpty())
                Toast.makeText(applicationContext, "Поле почтового адреса не заполнено", Toast.LENGTH_SHORT).show()
            else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailValid).matches())
                Toast.makeText(applicationContext,  "Неверный формат почтового адреса", Toast.LENGTH_SHORT).show()
            else if (passwordValid.isEmpty())
                Toast.makeText(applicationContext, "Поле пароля не введено", Toast.LENGTH_SHORT).show()
            else if (passwordValid.length < 8)
                Toast.makeText(applicationContext,"Пароль меньше 6 символов", Toast.LENGTH_SHORT).show()
            else if (repeatPasswordValid.isEmpty())
                Toast.makeText(applicationContext, "Поле пароля (повторное) не введено", Toast.LENGTH_SHORT).show()
            else if (passwordValid != repeatPasswordValid)
                Toast.makeText(applicationContext, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
            else {
                val intent: Intent = Intent(this, hello::class.java)
                intent.putExtra("userName", userName.text.toString())
                startActivity(intent)
            }
        }

        backTextView.setOnClickListener {
            val intent: Intent = Intent(this@Registration,MainActivity::class.java)
            startActivity(intent)
        }
    }

}