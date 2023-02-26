package com.example.jobportal.ui.job_dashboard

data class JobOfferData(
    val id: Int = 0,
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
