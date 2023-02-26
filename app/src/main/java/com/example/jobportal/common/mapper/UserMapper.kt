package com.example.jobportal.common.mapper

import com.example.jobportal.common.local.entity.UserEntity
import com.example.jobportal.ui.data.UserData

fun UserEntity.toUserData(): UserData {
    return UserData(
        id.toString(), name!!, mobile!!, email!!, company, password!!, type!!
    )
}

fun UserData.toUserEntity(): UserEntity {
    return UserEntity(
        name = name,
        mobile = mobile,
        email = email,
        company = company,
        password = password,
        type = type
    )
}