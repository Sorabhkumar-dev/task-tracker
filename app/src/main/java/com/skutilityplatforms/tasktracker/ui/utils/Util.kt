package com.skutilityplatforms.tasktracker.ui.utils

import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

object Util {
    fun getFormattedDate(date: Date): String =
        SimpleDateFormat("dd MMM yyy hh:mm aa", Locale.getDefault()).format(date)

    fun showToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun scheduledAtTimeInterval(timeMillis: Long, schedule: () -> Unit) {
        Timer().schedule(object : TimerTask() {
            override fun run() {
                schedule()
            }
        }, timeMillis)
    }
}