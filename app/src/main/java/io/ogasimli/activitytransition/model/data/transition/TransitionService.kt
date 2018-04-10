/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.model.data.transition

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.google.android.gms.location.ActivityRecognition
import com.google.android.gms.location.ActivityTransitionRequest
import dagger.android.AndroidInjection
import io.ogasimli.activitytransition.helper.constants.Constants.FOREGROUND_SERVICE_NOTIFICATION_ID
import io.ogasimli.activitytransition.helper.rx.EventType
import io.ogasimli.activitytransition.helper.rx.RxBus
import io.ogasimli.activitytransition.helper.rx.RxEvent
import timber.log.Timber
import javax.inject.Inject

/**
 * Service used for tracking activity transitions in the background.
 *
 * @author Orkhan Gasimli on 07.08.2017.
 */
class TransitionService : Service() {

    companion object {
        var isInProgress = false
    }

    @Inject
    lateinit var request: ActivityTransitionRequest

    @Inject
    lateinit var pendingIntent: PendingIntent

    @Inject
    lateinit var notification: Notification

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        // Inject external dependencies
        AndroidInjection.inject(this)
        super.onCreate()
        Timber.i("Created.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        // Promote service to foreground
        promoteToForeground()

        // Decide weather to start or stop the location & activity updates
        startTransitionUpdate()

        return Service.START_STICKY
    }

    override fun onDestroy() {

        // Decide weather to start or stop the location & activity updates
        stopTransitionUpdates()

        super.onDestroy()
    }

    // Promote background service to foreground
    private fun promoteToForeground() {
        startForeground(FOREGROUND_SERVICE_NOTIFICATION_ID, notification)
    }

    /**
     * Start tracking activity transitions
     */
    private fun startTransitionUpdate() {
        // Start updates
        val task = ActivityRecognition
                .getClient(this)
                .requestActivityTransitionUpdates(request, pendingIntent)

        task.addOnSuccessListener {
            Timber.d("Successfully started")
            isInProgress = true
            RxBus.publish(RxEvent(EventType.TRANSITION_UPDATE))
        }

        task.addOnFailureListener { e ->
            Timber.e(e)
            stopSelf()
        }
    }

    /**
     * Stop tracking activity transitions
     */
    private fun stopTransitionUpdates() {
        // Stop updates
        val task = ActivityRecognition
                .getClient(this)
                .removeActivityTransitionUpdates(pendingIntent)

        task.addOnSuccessListener {
            Timber.d("Successfully stopped")
            pendingIntent.cancel()
            isInProgress = false
            RxBus.publish(RxEvent(EventType.TRANSITION_UPDATE))
        }

        task.addOnFailureListener { e ->
            Timber.e(e)
        }
    }
}