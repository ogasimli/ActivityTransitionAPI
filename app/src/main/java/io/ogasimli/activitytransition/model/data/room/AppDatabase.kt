/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.model.data.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import io.ogasimli.activitytransition.model.models.TransitionEvent

/**
 * The Room database that contains the trips table
 *
 * @author Orkhan Gasimli on 14.02.2018.
 */
const val APP_DB_VERSION = 1

@Database(entities = [TransitionEvent::class], version = APP_DB_VERSION)
@TypeConverters(ActivityTypeConverter::class, TransitionTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTransitionDao(): TransitionDao
}
