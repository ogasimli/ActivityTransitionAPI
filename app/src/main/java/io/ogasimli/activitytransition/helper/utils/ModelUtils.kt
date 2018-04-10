/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.helper.utils

import io.ogasimli.activitytransition.R
import io.ogasimli.activitytransition.model.models.ActivityType
import io.ogasimli.activitytransition.model.models.TransitionEvent
import io.ogasimli.activitytransition.model.models.TransitionType

/**
 * Extension functions for manipulating data classes
 *
 * @author Orkhan Gasimli on 01.03.2018.
 */

fun TransitionEvent.stringify(): String {
    val stringBuilder = StringBuilder()
    stringBuilder
            .append(this.stringifyTransition())
            .append(": ")
            .append(this.stringifyActivity())
    return stringBuilder.toString()
}

private fun TransitionEvent.stringifyActivity() = when (this.activityType) {
    ActivityType.RUNNING -> "Running"
    ActivityType.IN_VEHICLE -> "Driving"
    ActivityType.ON_BICYCLE -> "Driving bike"
    ActivityType.WALKING -> "Walking"
    ActivityType.STILL -> "Still"
    ActivityType.UNKNOWN -> "Unknown"
}

private fun TransitionEvent.stringifyTransition() = when (this.transitionType) {
    TransitionType.ENTER -> "Start"
    TransitionType.EXIT -> "Stop"
}

fun TransitionEvent.toDrawable() = when (this.transitionType) {
    TransitionType.ENTER -> when (this.activityType) {
        ActivityType.RUNNING -> R.drawable.ic_run_enter
        ActivityType.IN_VEHICLE -> R.drawable.ic_drive_enter
        ActivityType.ON_BICYCLE -> R.drawable.ic_bike_enter
        ActivityType.WALKING -> R.drawable.ic_walk_enter
        ActivityType.STILL -> R.drawable.ic_still_enter
        ActivityType.UNKNOWN -> R.drawable.ic_unknown_enter
    }
    TransitionType.EXIT -> when (this.activityType) {
        ActivityType.RUNNING -> R.drawable.ic_run_exit
        ActivityType.IN_VEHICLE -> R.drawable.ic_drive_exit
        ActivityType.ON_BICYCLE -> R.drawable.ic_bike_exit
        ActivityType.WALKING -> R.drawable.ic_walk_exit
        ActivityType.STILL -> R.drawable.ic_still_exit
        ActivityType.UNKNOWN -> R.drawable.ic_unknown_exit
    }
}

fun activityTypeFromInt(value: Int) = when (value) {
    0 -> ActivityType.IN_VEHICLE
    1 -> ActivityType.ON_BICYCLE
    3 -> ActivityType.STILL
    7 -> ActivityType.WALKING
    8 -> ActivityType.RUNNING
    else -> ActivityType.UNKNOWN
}

fun transitionTypeFromInt(value: Int) = when (value) {
    0 -> TransitionType.ENTER
    else -> TransitionType.EXIT
}