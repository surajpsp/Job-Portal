package com.example.jobportal.common

sealed class Resource<T>(
    val data: T? = null,
    val msg: String? = null
) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(msg: String?) : Resource<T>(msg = msg)
    class Loading<T>(val isLoading: Boolean = true) : Resource<T>()
}