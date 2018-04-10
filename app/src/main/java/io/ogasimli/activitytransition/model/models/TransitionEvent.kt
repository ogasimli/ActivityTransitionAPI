/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.model.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.google.android.gms.location.ActivityTransitionEvent
import io.ogasimli.activitytransition.helper.utils.activityTypeFromInt
import io.ogasimli.activitytransition.helper.utils.transitionTypeFromInt
import kotlinx.android.parcel.Parcelize

/**
 * Data class holding trip data
 *
 * @author Orkhan Gasimli on 13.02.2018.
 */
@Parcelize
@Entity(tableName = "transitions",
        // Index id fields and force them to be unique
        indices = [(Index(value = ["id"], unique = true))])
data class TransitionEvent(
        @PrimaryKey(autoGenerate = true)
        var id: Long? = null,
        val activityType: ActivityType,
        val transitionType: TransitionType,
        val elapsedRealTimeNanos: Long,
        val eventTimeMillis: Long = System.currentTimeMillis()) : Parcelable {

    @Ignore
    constructor(activity: ActivityTransitionEvent) : this(
            activityType = activityTypeFromInt(activity.activityType),
            transitionType = transitionTypeFromInt(activity.transitionType),
            elapsedRealTimeNanos = activity.elapsedRealTimeNanos)
}