package com.bertholucci.home.extensions

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date
import java.util.Calendar

const val DATE_PATTERN_1 = "yyyy-MM-dd"
const val DATE_PATTERN_2 = "dd MMM yyyy"

fun String.getYear(): String {
    val dateFormatted = SimpleDateFormat(DATE_PATTERN_1, Locale.getDefault()).parse(this) ?: Date()
    return Calendar.getInstance().apply { time = dateFormatted }.get(Calendar.YEAR).toString()
}

fun String.getDateFormatted(pattern: String = DATE_PATTERN_1, toPattern: String): String {
    val dateFormatted = SimpleDateFormat(pattern, Locale.getDefault()).parse(this) ?: Date()
    return SimpleDateFormat(toPattern, Locale.US).format(dateFormatted)
}

fun String.ifNotEmpty(defaultValue: () -> String) = if (isNotEmpty()) defaultValue() else this
