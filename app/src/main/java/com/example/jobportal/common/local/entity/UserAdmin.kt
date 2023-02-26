package com.example.jobportal.common.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String?,
    val mobile: String?,
    val email: String?,
    val company: String?,
    val password: String?,
    val type: UserType?
)

enum class UserType {
    RECRUITER, SEEKER
}
