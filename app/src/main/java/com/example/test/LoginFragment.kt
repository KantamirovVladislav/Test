package com.example

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.test.R
import com.example.test.databinding.ActivityLoginBinding
import com.example.test.databinding.ActivityMainBinding
import com.example.test.hello
import com.example.test.manager.DBRoom
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!
    private val APP_SETTINGS: String = "app_settings"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dbManager = DBRoom(requireContext())

        // Переход к экрану регистрации
        binding.textView3.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, RegistrationFragment())
                .addToBackStack(null)
                .commit()
        }

        // Обработка авторизации
        binding.buttonSignIn.setOnClickListener {
            val userNameValid = binding.editTextAccountAuthorization.text.toString().trim()
            val passwordValid = binding.editTextPassword.text.toString().trim()

            lifecycleScope.launch {
                if (userNameValid.isEmpty()) {
                    showToast(getString(R.string.login_not_fill))
                } else if (userNameValid.length < 5) {
                    showToast(getString(R.string.login_less_5))
                } else if (passwordValid.isEmpty()) {
                    showToast(getString(R.string.password_not_fill))
                } else if (passwordValid.length < 8) {
                    showToast(getString(R.string.password_less_8))
                } else if (!dbManager.checkPassword(userNameValid, passwordValid)) {
                    showToast(getString(R.string.invalid_username_or_password))
                } else {
                    val intent = Intent(requireContext(), hello::class.java)
                    intent.putExtra("userName", userNameValid)
                    startActivity(intent)
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
