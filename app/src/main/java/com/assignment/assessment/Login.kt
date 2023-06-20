package com.assignment.assessment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.assignment.assessment.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

          validateForm()

        }
    }

    fun validateForm() {
        val confirmEmail = binding.etconfirmEmail.text.toString()
        val confirmPassword = binding.etConfPassword.text.toString()
        var error = false

        if (confirmEmail.isBlank()) {
            binding.tilconfirmEmail.error = "Email  is required"
            error = true

        }

       if (confirmPassword.isBlank()) {
          binding.tilconfPassword.error = " Password is required"
          error = true

       }

    }

}

//        if (!error) {
//            val intent = Intent(this, addedUser::class.java)
//            startActivity(intent)
//            Toast.makeText(this, "$firstName $lastName $email", Toast.LENGTH_LONG).show()
//            finish()
//        }
//    }
//
//        fun clearErrors() {
//            binding.tilFirstName.error = null
//            binding.tilLastName.error = null
//            binding.tilPhoneNumber.error = null
//            binding.tilEmail.error = null
//        }
//    }