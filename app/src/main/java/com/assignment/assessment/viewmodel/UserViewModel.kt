package com.assignment.assessment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.assessment.model.RegisterRequest
import com.assignment.assessment.model.RegisterResponse
import com.assignment.assessment.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {
    val userRepo=UserRepository()
    val regLiveData= MutableLiveData<RegisterResponse>()
    val errLiveData=MutableLiveData<String>()


    fun registerUser(registerRequest: RegisterRequest){
        viewModelScope.launch {
            val response = userRepo.registerUser(registerRequest)


            if (response.isSuccessful){
                regLiveData.postValue(response.body())
            }
            else{
                errLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}

//private fun <T> MutableLiveData<T>.postValue(body: RegisterRequest?) {
//
//}

