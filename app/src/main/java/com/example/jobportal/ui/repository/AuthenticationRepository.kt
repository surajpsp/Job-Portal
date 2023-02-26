package com.example.jobportal.ui.repository

import com.example.jobportal.common.Resource
import com.example.jobportal.ui.data.UserData
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    suspend fun addUser(
        userData: UserData
    ): Flow<Resource<Boolean>>

    suspend fun login(
        email: String,pasword:String
    ): Flow<Resource<UserData>>
}

