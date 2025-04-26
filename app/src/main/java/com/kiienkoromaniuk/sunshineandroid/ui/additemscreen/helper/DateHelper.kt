package com.kiienkoromaniuk.sunshineandroid.ui.additemscreen.helper

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.concurrent.TimeUnit

object DateHelper {
    fun dateWithDashesFromMilliseconds(milliseconds: Long?): String? {
        return milliseconds?.let {
            val date = LocalDate.ofEpochDay(TimeUnit.MILLISECONDS.toDays(milliseconds))
            return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString()
        }
    }

    fun formatDate(date: String?): String {
        val dateTime = OffsetDateTime.parse(date)

        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm", Locale.ENGLISH)
        return dateTime.format(formatter)
    }
}
