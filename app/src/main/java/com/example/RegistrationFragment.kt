package com.example

import com.example.test.R
import com.example.test.hello

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.test.databinding.ActivityRegistrationBinding
import com.example.test.manager.DBRoom
import com.example.test.manager.User
import kotlinx.coroutines.launch

class RegistrationFragment : Fragment() {

    private var _binding: ActivityRegistrationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActivityRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dbManager = DBRoom(requireContext())

        binding.buttonReg.setOnClickListener {
            val userNameValid = binding.editTextAccountRegistration.text.toString().trim()
            val emailValid = binding.editTextEmail.text.toString().trim()
            val passwordValid = binding.editTextPassword.text.toString().trim()
            val repeatPasswordValid = binding.editTextRepeatPassword.text.toString().trim()

            lifecycleScope.launch {
                when {
                    userNameValid.isEmpty() -> showToast(getString(R.string.login_not_fill))
                    userNameValid.length < 5 -> showToast(getString(R.string.login_less_5))
                    emailValid.isEmpty() -> showToast(getString(R.string.email_not_fill))
                    !android.util.Patterns.EMAIL_ADDRESS.matcher(emailValid).matches() -> showToast(getString(R.string.invalid_email_address))
                    passwordValid.isEmpty() -> showToast(getString(R.string.password_not_fill))
                    passwordValid.length < 8 -> showToast(getString(R.string.password_less_8))
                    repeatPasswordValid.isEmpty() -> showToast(getString(R.string.repestPassword_not_fill))
                    passwordValid != repeatPasswordValid -> showToast(getString(R.string.password_not_match))
                    dbManager.checkUserName(userNameValid) -> showToast(getString(R.string.username_exist))
                    dbManager.checkEmail(emailValid) -> showToast(getString(R.string.email_exist))
                    else -> {
                        dbManager.db.insertUsers(User(0, userNameValid, emailValid, passwordValid, 1))
                        startActivity(Intent(requireContext(), hello::class.java))
                    }
                }
            }
        }

        binding.textViewBackRegistration.setOnClickListener {
            parentFragmentManager.popBackStack()
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
