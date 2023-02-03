package com.bertholucci.home.extensions

import java.time.LocalDate
import java.time.format.DateTimeFormatter

const val DATE_PATTERN_1 = "uuuu-MM-dd"
const val DATE_PATTERN_2 = "dd MMM yyyy"

fun String.getYear() =
    LocalDate.parse(this, DateTimeFormatter.ofPattern(DATE_PATTERN_1)).year.toString()

fun String.getDateFormatted(pattern: String = DATE_PATTERN_1, toPattern: String): String =
    LocalDate.parse(this, DateTimeFormatter.ofPattern(pattern)).format(DateTimeFormatter.ofPattern(toPattern))

fun String.ifNotEmpty(defaultValue: () -> String) = if (isNotEmpty()) defaultValue() else this
