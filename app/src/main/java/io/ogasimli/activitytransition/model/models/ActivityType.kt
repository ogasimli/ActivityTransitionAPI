/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.model.models

/**
 * Enum class used to indicate the activity type
 *
 * @author Orkhan Gasimli on 18.02.2018.
 */
enum class ActivityType(val value: Int) {
    IN_VEHICLE(0),
    ON_BICYCLE(1),
    UNKNOWN(2),
    STILL(3),
    WALKING(7),
    RUNNING(8)
}