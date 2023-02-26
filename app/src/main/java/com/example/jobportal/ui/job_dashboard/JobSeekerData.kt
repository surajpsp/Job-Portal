package com.example.jobportal.ui.job_dashboard

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class JobSeekerData(
    val id: Int,
    val title: String,
    val name: String,
    val mobile: String,
    val email: String,
    val status: String,
    val date: String,
    val experiance: String
) : Parcelable
