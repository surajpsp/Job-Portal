package com.example.jobportal.data

import com.example.jobportal.common.local.entity.JobOfferEntity
import com.example.jobportal.common.local.entity.JobSeekerEntity

fun addFakeJobOffer(): List<JobOfferEntity> {
    val list: MutableList<JobOfferEntity> = mutableListOf()
    list.apply {
        add(
            JobOfferEntity(
                title = "Laravel Developer",
                budget = "5",
                experiance = "4",
                company = "Syska India Pvt Ltd.",
                jobType = "Hybrid",
                user = "Rahul Kumar",
                status = false,
                date = "10/10/2022",
                appliedDate = "24/10/2022"
            )
        )

        add(
            JobOfferEntity(
                title = "Flutter Developer",
                budget = "8",
                experiance = "1",
                company = "Bsk Pvt Ltd.",
                jobType = "WFO",
                user = "Anand Kumar",
                status = false,
                date = "10/10/2022",
                appliedDate = "24/10/2022"
            )
        )

        add(
            JobOfferEntity(
                title = "Html Developer",
                budget = "5",
                experiance = "4",
                company = "Hem India Pvt Ltd.",
                jobType = "WFH",
                user = "Kavya Kumar",
                status = false,
                date = "10/10/2022",
                appliedDate = "24/10/2022"
            )
        )

        add(
            JobOfferEntity(
                title = "Android Developer",
                budget = "5",
                experiance = "4",
                company = "Sip Pvt Ltd.",
                jobType = "Hybrid",
                user = "Jassi Gill",
                status = false,
                date = "10/10/2022",
                appliedDate = "24/10/2022"
            )
        )

        add(
            JobOfferEntity(
                title = "Android Developer",
                budget = "10",
                experiance = "8",
                company = "Syska India Pvt Ltd.",
                jobType = "Hybrid",
                user = "Rahul Kumar",
                status = false,
                date = "10/10/2022",
                appliedDate = "24/10/2022"
            )
        )

        add(
            JobOfferEntity(
                title = "Android Developer",
                budget = "5",
                experiance = "4",
                company = "Syska India Pvt Ltd.",
                jobType = "Hybrid",
                user = "Sing Kumar",
                status = false,
                date = "10/10/2022",
                appliedDate = "24/10/2022"
            )
        )

        add(
            JobOfferEntity(
                title = "Laravel Developer",
                budget = "5",
                experiance = "4",
                company = "Om Pvt Ltd.",
                jobType = "Hybrid",
                user = "Rohit Kumar",
                status = false,
                date = "10/10/2022",
                appliedDate = "24/10/2022"
            )
        )

        add(
            JobOfferEntity(
                title = "Ios Developer",
                budget = "5",
                experiance = "4",
                company = "Hari India Pvt Ltd.",
                jobType = "Hybrid",
                user = "Rahul Kumar",
                status = false,
                date = "10/10/2022",
                appliedDate = "24/10/2022"
            )
        )

    }

    return list.toList()
}


fun addFakeJobSeeker(): List<JobSeekerEntity> {
    val list: MutableList<JobSeekerEntity> = mutableListOf()
    list.apply {
        add(
            JobSeekerEntity(
                title = "Android Developer",
                name = "Jassi Gill",
                mobile = "9876543210",
                email = "jassigill@emai.com",
                status = "false",
                date = "12/10/2022",
                experiance = "4"
            )
        )

        add(
            JobSeekerEntity(
                title = "Laravel Developer",
                name = "Neha Gill",
                mobile = "9876543210",
                email = "neaha@emai.com",
                status = "false",
                date = "12/10/2022",
                experiance = "4"
            )
        )

        add(
            JobSeekerEntity(
                title = "Android Developer",
                name = "Aman Kumar",
                mobile = "9876543210",
                email = "amamn@emai.com",
                status = "false",
                date = "12/10/2022",
                experiance = "4"
            )
        )

        add(
            JobSeekerEntity(
                title = "Full Stack Developer",
                name = "Balwinder Kumar",
                mobile = "9876543210",
                email = "jassigill@emai.com",
                status = "false",
                date = "12/10/2022",
                experiance = "4"
            )
        )

        add(
            JobSeekerEntity(
                title = "JQuery Developer",
                name = "Rohit Sing",
                mobile = "9876543210",
                email = "jassigill@emai.com",
                status = "false",
                date = "12/10/2022",
                experiance = "4"
            )
        )

        add(
            JobSeekerEntity(
                title = "Android Developer",
                name = "Jassi Gill",
                mobile = "9876543210",
                email = "jassigill@emai.com",
                status = "false",
                date = "12/10/2022",
                experiance = "4"
            )
        )

        add(
            JobSeekerEntity(
                title = "Android Developer",
                name = "Harman singh",
                mobile = "9876543210",
                email = "jassigill@emai.com",
                status = "false",
                date = "12/10/2022",
                experiance = "4"
            )
        )

        add(
            JobSeekerEntity(
                title = "Full Stack Developer",
                name = "Prince Agrawal",
                mobile = "9876543210",
                email = "jassigill@emai.com",
                status = "false",
                date = "12/10/2022",
                experiance = "4"
            )
        )

    }

    return list.toList()
}