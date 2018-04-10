/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.model.data.room

import android.arch.persistence.room.TypeConverter
import io.ogasimli.activitytransition.model.models.ActivityType

/**
 * TypeConverter class for Room to allow {@link ActivityType} enum
 * to be converted to string and vice versa
 *
 * @author Orkhan Gasimli on 28.02.2018.
 */
class ActivityTypeConverter {

    @TypeConverter
    fun activityTypeToString(type: ActivityType): String = type.name

    @TypeConverter
    fun stringToActivityType(type: String): ActivityType= ActivityType.valueOf(type)
}