package com.williamzabot.events.presenter.extensions

fun String.emailIsValid() : Boolean{
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}