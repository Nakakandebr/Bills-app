package com.assignment.assessment.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.assignment.assessment.HomeActivity
import com.assignment.assessment.databinding.ActivityLoginBinding
import com.assignment.assessment.model.LoginRequest
import com.assignment.assessment.viewmodel.LoginViewModel
import com.assignment.assessment.viewmodel.UserViewModel

class Login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
     val userViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.btnloginn.setOnClickListener {
            validateForm()

        }
        userViewModel.errLiveData.observe(this, Observer {
            err->Toast.makeText(this,err,Toast.LENGTH_LONG).show()
            binding.progressBar2.visibility=View.GONE
        })
        userViewModel.loginLiveData.observe(this, Observer { loginResponse->binding.progressBar2.visibility=View.GONE
            Toast.makeText(this,loginResponse.message,Toast.LENGTH_LONG).show()
            startActivity(Intent(this,HomeActivity::class.java))
        })

    }

    private fun validateForm() {
        val email = binding.etconfirmEmail.text.toString()
        val password = binding.etConfPassword.text.toString()
        var error = false

        if (email.isBlank()) {
            binding.tilconfirmEmail.error = "Email  is required"
            error = true

        }

       if (password.isBlank()) {
          binding.tilconfPassword.error = " Password is required"
          error = true

       }
        if(!error){
            val loginRequest=LoginRequest(
                email=email,
                password=password

            )
            binding.progressBar2.visibility=View.VISIBLE
            userViewModel.loginUser(loginRequest)
        }

    }

}

