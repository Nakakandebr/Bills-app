package com.assignment.assessment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.assessment.model.LoginRequest
import com.assignment.assessment.model.LoginResponse
import com.assignment.assessment.model.RegisterRequest
import com.assignment.assessment.model.RegisterResponse
import com.assignment.assessment.repository.LoginRepository
import com.assignment.assessment.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel() {
    val loginRepo= LoginRepository()
    val loginLiveData= MutableLiveData<LoginResponse>()
    val errLiveData= MutableLiveData<String>()


    fun loginUser(loginRequest: LoginRequest){
        viewModelScope.launch {
            val response = loginRepo.loginUser(loginRequest)


            if (response.isSuccessful){
                loginLiveData.postValue(response.body())
            }
            else{
                errLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}

