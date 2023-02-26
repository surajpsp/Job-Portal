package com.example.jobportal.common.mapper

import com.example.jobportal.common.local.entity.JobSeekerEntity
import com.example.jobportal.ui.job_dashboard.JobSeekerData

fun JobSeekerEntity.toJobSeekerData(): JobSeekerData {
    return JobSeekerData(
        id = id,
        title = title,
        name = name,
        mobile = mobile,
        email, status, date, experiance
    )
}


fun JobSeekerData.toJobSeekerEntity(): JobSeekerEntity {
    return JobSeekerEntity(
        title = title,
        name = name,
        mobile = mobile,
        email = email,
        status = status,
        date = date,
        experiance = experiance
    )
}