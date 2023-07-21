package com.assignment.assessment.repository

import com.assignment.assessment.api.ApiInterface
import com.assignment.assessment.api.Apiclient
import com.assignment.assessment.model.LoginRequest
import com.assignment.assessment.model.LoginResponse

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class LoginRepository {

    val apiClient= Apiclient.buildClient(ApiInterface::class.java)

    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse> {
        return withContext(Dispatchers.IO){
            apiClient.loginUser(loginRequest)
        }
    }
}