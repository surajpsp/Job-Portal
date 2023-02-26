package com.example.jobportal.ui.registration

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jobportal.common.Resource
import com.example.jobportal.common.local.entity.UserType
import com.example.jobportal.ui.data.UserData
import com.example.jobportal.ui.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationModel @Inject constructor(
    private val userRepository: AuthenticationRepository
) : ViewModel() {

    private val TAG = "RegistrationModel"

    private val resultState = MutableStateFlow<Resource<Boolean>>(Resource.Loading(false))
    val state = resultState.asStateFlow()

    private val _loginState = MutableStateFlow<Resource<UserData>>(Resource.Loading(false))
    val loginState: StateFlow<Resource<UserData>> = _loginState.asStateFlow()


    fun onLogin(email: String, password: String) = viewModelScope.launch {
        val response = userRepository.login(email, password)

        response.collect {
            Log.d(TAG, "onLogin: collect - $it")
            _loginState.value = it
        }
    }

    fun registerNow(
        name: String,
        email: String,
        mobile: String,
        password: String,
        companyName: String,
        type: Boolean
    ) = viewModelScope.launch {
        val result = userRepository.addUser(
            UserData(
                name = name,
                email = email,
                mobile = mobile,
                password = password,
                company = companyName,
                type = if (type) UserType.RECRUITER else UserType.SEEKER
            )
        )

        result.collect {
            Log.d(TAG, "registerNow: collect - $it")
            resultState.value = it
//            when (it) {
//                is Resource.Error -> onRegisterError(it.msg!!)
//                is Resource.Loading -> onRegisterLoading(it.isLoading)
//                is Resource.Success -> onRegisterSuccess()
//            }
        }

    }
}