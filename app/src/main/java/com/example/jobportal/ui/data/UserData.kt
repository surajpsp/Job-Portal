package com.example.jobportal.ui.data

import android.os.Parcelable
import com.example.jobportal.common.local.entity.UserType
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserData(
    val id: String? = null,
    val name: String,
    val mobile: String,
    val email: String,
    val company: String?,
    val password: String,
    val type: UserType
) : Parcelable
