package com.assignment.assessment.repository


import com.assignment.assessment.api.ApiInterface
import com.assignment.assessment.api.Apiclient
import com.assignment.assessment.model.RegisterRequest
import com.assignment.assessment.model.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    val apiClient= Apiclient.buildClient(ApiInterface::class.java)

    suspend fun registerUser(registerRequest: RegisterRequest): Response<RegisterResponse> {
        return withContext(Dispatchers.IO){
            apiClient.registerUser(registerRequest)
        }
    }
}
