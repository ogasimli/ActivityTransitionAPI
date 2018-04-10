/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.helper.constants

import io.ogasimli.activitytransition.BuildConfig

/**
 * Helper class to hold constants
 *
 * @author Orkhan Gasimli on 12.02.2018.
 */

object Constants {

    /**
     * Package firstName of the app
     */
    @JvmStatic
    private val PACKAGE_NAME = BuildConfig.APPLICATION_ID

    /**
     * Default key of SharedPreferences
     */
    @JvmStatic
    val DEFAULT_SHARED_PREF_KEY = "$PACKAGE_NAME.SHARED_PREF_KEY"

    /**
     * Background service channel ID
     */
    @JvmStatic
    val TRANSITION_SERVICE_CHANNEL_ID = "$PACKAGE_NAME.TRANSITION_SERVICE_CHANNEL_ID"

    /**
     * Default channel
     */
    @JvmStatic
    val DEFAULT_CHANNEL_ID = "$PACKAGE_NAME.DEFAULT_CHANNEL_ID"

    /**
     * Notification ID for foreground service
     */
    @JvmStatic
    val FOREGROUND_SERVICE_NOTIFICATION_ID = 200
}