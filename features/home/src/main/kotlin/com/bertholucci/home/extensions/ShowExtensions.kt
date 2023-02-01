package com.bertholucci.home.extensions

import com.bertholucci.domain.model.Schedule
import com.bertholucci.domain.model.Show
import com.bertholucci.home.model.ShowStatus

fun Show.getAirDate(): String {
    if (premiered.none()) return ""

    var year = premiered.getYear()

    if (status == ShowStatus.ENDED.value) {
        year = year.plus(" - ${ended.getYear()}")
    }
    return year
}

fun Schedule.getSchedule(): String {
    return "Every ${days.joinToString(", ")} at $time"
}