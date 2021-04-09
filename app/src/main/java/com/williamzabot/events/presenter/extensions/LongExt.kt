package com.williamzabot.events.presenter.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Long.toTime(): String {
    val date = Date(this)
    val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    return formatter.format(date)
}