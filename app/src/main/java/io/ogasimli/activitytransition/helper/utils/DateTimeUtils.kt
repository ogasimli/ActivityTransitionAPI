/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.helper.utils

import java.text.DateFormat

/**
 * Class holding static methods used for manipulating date & time
 *
 * @author Orkhan Gasimli on 25.12.2017.
 */

/**
 * Helper method to get and change color of drawable
 *
 * @param timeInMillis  UNIX time in milliseconds
 * @return              return string representation of formatted time
 */
fun convertToDateTime(timeInMillis: Long): String {
    // Create a DateFormatter object for displaying date in specified format.
    val formatter = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM)
    return formatter.format(timeInMillis)
}