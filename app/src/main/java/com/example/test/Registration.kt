package com.example.test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.test.manager.DBManager
import com.example.test.manager.DBRoom
import com.example.test.manager.User
import kotlinx.coroutines.CoroutineScope

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

        //val dbManager = DBManager(this)
        val dbManager = DBRoom(this)

        regBtn.setOnClickListener{

            val userNameValid: String = userName.text.toString().trim()
            val emailValid: String = email.text.toString().trim()
            val passwordValid: String = password.text.toString().trim()
            val repeatPasswordValid: String = repeatPassword.text.toString().trim()

            try {
                lifecycleScope.launch {
                    if (userNameValid.isEmpty())
                        Toast.makeText(applicationContext, getString(R.string.login_not_fill) , Toast.LENGTH_SHORT).show()
                    else if (userNameValid.length < 5)
                        Toast.makeText(applicationContext, getString(R.string.login_less_5), Toast.LENGTH_SHORT).show()
                    else if (emailValid.isEmpty())
                        Toast.makeText(applicationContext, getString(R.string.email_not_fill), Toast.LENGTH_SHORT).show()
                    else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailValid).matches())
                        Toast.makeText(applicationContext,  getString(R.string.invalid_email_address), Toast.LENGTH_SHORT).show()
                    else if (passwordValid.isEmpty())
                        Toast.makeText(applicationContext, getString(R.string.password_not_fill), Toast.LENGTH_SHORT).show()
                    else if (passwordValid.length < 8)
                        Toast.makeText(applicationContext,getString(R.string.password_less_8), Toast.LENGTH_SHORT).show()
                    else if (repeatPasswordValid.isEmpty())
                        Toast.makeText(applicationContext, getString(R.string.repestPassword_not_fill), Toast.LENGTH_SHORT).show()
                    else if (passwordValid != repeatPasswordValid)
                        Toast.makeText(applicationContext, getString(R.string.password_not_match), Toast.LENGTH_SHORT).show()
                    else if (dbManager.checkUserName(userNameValid))
                        Toast.makeText(applicationContext, getString(R.string.username_exist), Toast.LENGTH_SHORT).show()
                    else if (dbManager.checkEmail(emailValid))
                        Toast.makeText(applicationContext, getString(R.string.email_exist), Toast.LENGTH_SHORT).show()
                    else {
                        dbManager.db.insertUsers(User(0,userNameValid, emailValid, passwordValid, 1))
                        val intent: Intent = Intent(this@Registration, hello::class.java)
                        startActivity(intent)
                    }
                }
            }catch (ex: Exception){
                Toast.makeText(applicationContext, "${ex.message}", Toast.LENGTH_SHORT).show()
            }

        }


        backTextView.setOnClickListener {
            val intent: Intent = Intent(this@Registration,MainActivity::class.java)
            startActivity(intent)
        }
    }

}