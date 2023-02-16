package com.skutilityplatforms.tasktracker.ui.utils

import java.text.SimpleDateFormat
import java.util.*

object Util {
    fun getFormattedDate(date: Date) =
        SimpleDateFormat("dd MM yyy hh:mm aa", Locale.getDefault()).format(date)
}