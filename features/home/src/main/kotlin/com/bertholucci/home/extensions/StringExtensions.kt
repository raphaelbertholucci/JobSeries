package com.bertholucci.home.extensions

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.getYear() =
    LocalDate.parse(this, DateTimeFormatter.ofPattern("uuuu-MM-dd")).year.toString()

fun String.ifNotEmpty(defaultValue: () -> String) = if (isNotEmpty()) defaultValue() else this