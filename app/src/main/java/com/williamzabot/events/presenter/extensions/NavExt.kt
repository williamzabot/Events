package com.williamzabot.events.presenter.extensions

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import com.williamzabot.events.R

private val slideLeftOptions = NavOptions.Builder()
    .setEnterAnim(R.anim.slide_in_right)
    .setExitAnim(R.anim.slide_out_left)
    .setPopEnterAnim(R.anim.slide_in_left)
    .setPopExitAnim(R.anim.slide_out_right)
    .build()

fun NavController.navigateWithAnimations(
    destinationId: Int,
    animation: NavOptions = slideLeftOptions
) {
    this.navigate(destinationId, null, animation)
}

fun NavController.navigateWithAnimations(
    destinationId: NavDirections,
    animation: NavOptions = slideLeftOptions
) {
    this.navigate(destinationId, animation)
}

