/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.model.data.room

import android.arch.persistence.room.TypeConverter
import io.ogasimli.activitytransition.model.models.TransitionType

/**
 * TypeConverter class for Room to allow {@link TransitionType} enum
 * to be converted to string and vice versa
 *
 * @author Orkhan Gasimli on 28.02.2018.
 */
class TransitionTypeConverter {

    @TypeConverter
    fun transitionTypeToString(type: TransitionType): String = type.name

    @TypeConverter
    fun stringToTransitionType(type: String): TransitionType= TransitionType.valueOf(type)
}