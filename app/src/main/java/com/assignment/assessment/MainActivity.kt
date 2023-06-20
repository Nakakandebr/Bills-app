package com.assignment.assessment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.assignment.assessment.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            validateForm()
            clearErrors()
        }

    }

    fun validateForm() {
       val userName = binding.etUserName.text.toString()
        val phoneNumber = binding.etPhoneNumber.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        var error = false

        if (userName.isBlank()) {
            binding.tilUserName.error = "Username is required"
            error = true

        }

        if (phoneNumber.isBlank()) {
            binding.tilPhoneNumber.error = " PhoneNumber is required"
            error = true

        }

        if (email.isBlank()) {
            binding.tilEmail.error = "Email   is required"
            error = true

        }

        if (password.isBlank()) {
            binding.tilPassword.error = " email is required"
            error = true


        }

        if (!error) {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            Toast.makeText(this, "$userName $phoneNumber $email ", Toast.LENGTH_LONG).show()
            finish()
        }
    }

       fun clearErrors() {
         binding.tilUserName.error = null
           binding.tilPassword.error = null
           binding.tilPhoneNumber.error = null
            binding.tilEmail.error = null
        }
    }


