/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.model.data.transition

import android.app.IntentService
import android.content.Intent
import com.google.android.gms.location.ActivityTransitionResult
import dagger.android.AndroidInjection
import io.ogasimli.activitytransition.model.data.DataManager
import io.ogasimli.activitytransition.model.models.TransitionEvent
import timber.log.Timber
import javax.inject.Inject

/**
 * IntentService class processing all trip related data
 *
 * @author Orkhan Gasimli on 11.07.2017.
 */
class TransitionIntentService : IntentService("TransitionIntentService") {

    @Inject
    lateinit var dataManager: DataManager

    override fun onCreate() {
        // Inject external dependencies
        AndroidInjection.inject(this)
        super.onCreate()
        Timber.i("Created.")
    }

    override fun onHandleIntent(intent: Intent?) {
        if (ActivityTransitionResult.hasResult(intent)) {
            val result = ActivityTransitionResult.extractResult(intent)
            result?.let {
                for (event in it.transitionEvents) {
                    event?.run {
                        // chronological sequence of events....
                        Timber.d(this.toString())
                        val transitionEvent = TransitionEvent(this)
                        dataManager.insertEvent(transitionEvent)
                    }
                }
            }
        }
    }
}