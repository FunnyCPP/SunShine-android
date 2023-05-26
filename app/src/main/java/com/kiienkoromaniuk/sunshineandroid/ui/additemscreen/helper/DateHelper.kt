package com.kiienkoromaniuk.sunshineandroid.ui.additemscreen.helper

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

object DateHelper {
    fun dateWithDashesFromMilliseconds(milliseconds: Long?): String? {
        return milliseconds?.let {
            val date = LocalDate.ofEpochDay(TimeUnit.MILLISECONDS.toDays(milliseconds))
            return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString()
        }
    }
}
