package com.example.jobportal.common.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class JobSeekerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val name: String,
    val mobile: String,
    val email: String,
    val status: String,
    val date: String,
    val experiance: String,
    val jobDescription:String
)