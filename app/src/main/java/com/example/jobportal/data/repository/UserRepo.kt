package com.example.jobportal.data.repository

import com.example.jobportal.common.Resource
import com.example.jobportal.common.local.dao.UserDao
import com.example.jobportal.common.mapper.toUserData
import com.example.jobportal.common.mapper.toUserEntity
import com.example.jobportal.ui.data.UserData
import com.example.jobportal.ui.repository.AuthenticationRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepo @Inject constructor(
    private val db: UserDao
) : AuthenticationRepository {

    override suspend fun addUser(userData: UserData): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Loading())
            db.insertAll(userData.toUserEntity())
            delay(3000)
            emit(Resource.Loading(false))
            emit(Resource.Success(data = true))
        }
    }

    override suspend fun login(email: String, pasword: String): Flow<Resource<UserData>> {
        return flow {
            emit(Resource.Loading())
            val response = db.findByEmail(email)
            delay(3000)
            emit(Resource.Loading(false))
            try {
                val password = response.password
                if (pasword == password) {
                    emit(Resource.Success(response.toUserData()))
                    return@flow
                }
                emit(Resource.Error("Password Not Matched!"))
            } catch (e: java.lang.NullPointerException) {
                emit(Resource.Error("User Not Found!"))
            }
        }
    }

}