/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.helper

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.support.annotation.RequiresApi
import io.ogasimli.activitytransition.R
import io.ogasimli.activitytransition.di.qualifier.ApplicationContext
import io.ogasimli.activitytransition.helper.constants.Constants.DEFAULT_CHANNEL_ID
import io.ogasimli.activitytransition.helper.constants.Constants.TRANSITION_SERVICE_CHANNEL_ID
import javax.inject.Inject

/**
 * Helper class for creating NotificationChannel
 *
 * @author Orkhan Gasimli on 11.01.2018.
 */
class NotificationChannelBuilder @Inject constructor(
        @ApplicationContext val context: Context,
        val notificationManager: NotificationManager) : ContextWrapper(context) {

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun createLocationChannel(): String {
        val channel = NotificationChannel(TRANSITION_SERVICE_CHANNEL_ID,
                getString(R.string.location_channel_name),
                NotificationManager.IMPORTANCE_LOW)
        createNotificationChannel(channel, TRANSITION_SERVICE_CHANNEL_ID)
        return TRANSITION_SERVICE_CHANNEL_ID
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun createDefaultChannel(): String {
        val channel = NotificationChannel(DEFAULT_CHANNEL_ID,
                getString(R.string.default_channel_name),
                NotificationManager.IMPORTANCE_DEFAULT)
        channel.enableVibration(true)
        createNotificationChannel(channel, DEFAULT_CHANNEL_ID)
        return DEFAULT_CHANNEL_ID
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun createNotificationChannel(channel: NotificationChannel, id: String) {
        if (notificationManager.getNotificationChannel(id) == null) {
            notificationManager.createNotificationChannel(channel)
        }
    }
}
