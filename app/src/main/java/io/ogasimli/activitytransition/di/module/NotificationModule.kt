/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.di.module

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import dagger.Module
import dagger.Provides
import io.ogasimli.activitytransition.R
import io.ogasimli.activitytransition.di.qualifier.ApplicationContext
import io.ogasimli.activitytransition.di.qualifier.LocationNotificationChannelId
import io.ogasimli.activitytransition.helper.NotificationChannelBuilder
import io.ogasimli.activitytransition.helper.constants.RequestCodes
import io.ogasimli.activitytransition.view.home.HomeActivity
import org.jetbrains.anko.intentFor
import javax.inject.Singleton


/**
 * DI Module providing app-level dependencies for showing notifications
 *
 * @author Orkhan Gasimli on 14.02.2018.
 */
@Module
class NotificationModule {

    /**
     * Provide NotificationManager
     */
    @Singleton
    @Provides
    fun provideNotificationManager(@ApplicationContext context: Context): NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    /**
     * Provide channel ID for notification
     */
    @Singleton
    @Provides
    @LocationNotificationChannelId
    fun provideLocationNotificationChannelId(
            notificationChannelBuilder: NotificationChannelBuilder): String =
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                notificationChannelBuilder.createLocationChannel()
            } else {
                ""
            }

    /**
     * Provide notification for running LocationService as foreground service
     */
    @Singleton
    @Provides
    fun provideLocationNotification(
            @ApplicationContext context: Context,
            @LocationNotificationChannelId channelId: String): Notification {
        val intent = context.intentFor<HomeActivity>().setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        val pendingIntent = PendingIntent.getActivity(context, RequestCodes.HOME_ACTIVITY_PI_CODE,
                intent, 0)
        return NotificationCompat.Builder(context, channelId)
                .setContentTitle(context.getString(R.string.app_name) + " is running")
                .setContentText("Touch to open.")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .build()
    }
}