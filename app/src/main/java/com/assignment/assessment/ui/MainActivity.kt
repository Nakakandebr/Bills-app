package com.assignment.assessment.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.assignment.assessment.R
import com.assignment.assessment.databinding.ActivityMainBinding
import com.assignment.assessment.model.LoginResponse
import com.assignment.assessment.model.RegisterRequest
import com.assignment.assessment.utils.Constants
import com.assignment.assessment.viewmodel.UserViewModel


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val userViewModel :UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    override fun onResume() {
        super.onResume()
        binding.btnlogin.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)

        }
        binding.btnRegister.setOnClickListener {
            validateSignUP()

        }
        userViewModel.errLiveData.observe(this, Observer { err->
            Toast.makeText(this ,err,  Toast.LENGTH_LONG).show()
            binding.progressBar.visibility = View.GONE
        })

        userViewModel.regLiveData.observe(this, Observer { regResponse->
            binding.progressBar.visibility = View.GONE
            Toast.makeText(this, regResponse.message , Toast.LENGTH_LONG).show()
            startActivity(Intent(this,Login::class.java))
        })


    }

    fun validateSignUP() {
        val firstName = binding.etusername.text.toString()
        val lastName = binding.etLastName.text.toString()
        val phoneNumber = binding.etphonenumber.text.toString()
        val email = binding.etemail.text.toString()
        val password = binding.etpassword.text.toString()
        val confirmpassword = binding.etConfirmPassword.text.toString()

        var error = false
        if (firstName.isBlank()) {
            binding.tilusername.error = "First Name is required"
            error = true
        }
        if (lastName.isBlank()) {
            binding.tilusername.error = "Last Name is required"
            error = true
        }
        if (phoneNumber.isBlank()) {
            binding.tilphonenumber.error = "phone number is required"
            error = true
        }
        if (email.isBlank()) {
            binding.tilemail.error = "Email is required"
            error = true
        }

        if (password.isBlank()) {
            binding.tilpassword.error = "password is required"
            error = true
        }
        if (confirmpassword.isBlank()) {
            binding.tilpassword.error = getString(R.string.confirm_password_is_required)
            error = true
        }


        if (!error) {

            val registerRequest = RegisterRequest(
                firstname = firstName,
                lastname = lastName,
                email = email,
                password = password,
                phoneNumber = phoneNumber
            )
            binding.progressBar.visibility = View.VISIBLE
            userViewModel.registerUser(registerRequest)
        }
    }
  fun redirectUser(){
val sharedPrefs = getSharedPreferences(Constants.PREFS , Context.MODE_PRIVATE)
      val userId = sharedPrefs.getString(Constants.USER_ID , Constants.EMPTY_STRING)
      if (userId.isNullOrBlank()){
          startActivity(Intent(this , Login::class.java))
      }
      else{
          startActivity(Intent(this,HomeActivity::class.java))
      }
  }
}


