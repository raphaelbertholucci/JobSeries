package com.bertholucci.home.extensions

import com.bertholucci.domain.model.Schedule
import com.bertholucci.domain.model.Show
import com.bertholucci.home.ShowStatus

fun Show.getAirDate(): String {
    var year = premiered.getYear()

    if (status == ShowStatus.ENDED.value) {
        year = year.plus(" - ${ended.getYear()}")
    }
    return year
}

fun Schedule.getSchedule(): String {
    return "Every ${days.joinToString(", ")} at $time"
}