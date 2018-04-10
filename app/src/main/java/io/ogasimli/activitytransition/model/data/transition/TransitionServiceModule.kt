/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.model.data.transition

import android.app.PendingIntent
import android.content.Context
import com.google.android.gms.location.ActivityTransition
import com.google.android.gms.location.ActivityTransitionRequest
import com.google.android.gms.location.DetectedActivity
import dagger.Module
import dagger.Provides
import io.ogasimli.activitytransition.di.qualifier.ApplicationContext
import io.ogasimli.activitytransition.helper.constants.RequestCodes
import org.jetbrains.anko.intentFor

/**
 * DI Module providing dependencies for TransitionService
 *
 * @author Orkhan Gasimli on 27.02.2018.
 */
@Module
class TransitionServiceModule {

    /**
     * Provide transitions request
     */
    @Provides
    fun provideTransitionsRequest() : ActivityTransitionRequest {
        val activities = listOf(DetectedActivity.IN_VEHICLE,
                DetectedActivity.ON_BICYCLE,
                DetectedActivity.WALKING,
                DetectedActivity.RUNNING,
                DetectedActivity.STILL)
        val transitions = mutableListOf<ActivityTransition>()
        activities.forEach { activity ->
            val enterTransition = ActivityTransition.Builder()
                    .setActivityType(activity)
                    .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER)
                    .build()

            val exitTransition = ActivityTransition.Builder()
                    .setActivityType(activity)
                    .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_EXIT)
                    .build()

            transitions.add(enterTransition)
            transitions.add(exitTransition)
        }
        return ActivityTransitionRequest(transitions)
    }

    /**
     * Callback for Transition events
     */
    @Provides
    fun provideTransitionPendingIntent(@ApplicationContext context: Context): PendingIntent {
        val intent = context.intentFor<TransitionIntentService>()
        return PendingIntent.getService(context, RequestCodes.TRANSITION_INTENT_SERVICE_PI_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }
}

