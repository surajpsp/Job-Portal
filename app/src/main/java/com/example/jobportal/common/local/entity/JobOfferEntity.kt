package com.example.jobportal.common.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class JobOfferEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val budget: String,
    val experiance: String,
    val company: String,
    val jobType: String,
    val user: String,
    val status: Boolean,
    val date: String,
    val appliedDate: String
)