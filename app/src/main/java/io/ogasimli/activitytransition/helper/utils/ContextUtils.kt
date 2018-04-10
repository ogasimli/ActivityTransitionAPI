/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.helper.utils

import android.content.Context
import android.content.Intent
import android.os.Build
import org.jetbrains.anko.bundleOf

/**
 * Class holding Context related static methods
 *
 * @author Orkhan Gasimli on 25.12.2017.
 */

/**
 * Extension function to start foreground services
 *
 * @param service   the intent of service to be started
 */
fun Context.startServiceForeground(service: Intent) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        startForegroundService(service)
    } else {
        startService(service)
    }
}

/**
 * Extension function to create intents
 *
 * @param action    the action to be added to the intent
 * @param flags     the flags to be added to the intent
 * @param extras    the extras to be added to the intent
 * @return          the created intent
 */
inline fun <reified T : Any> Context.intentFor(action: String? = null,
                                               flags: Array<Int>? = null,
                                               vararg extras: Pair<String, Any>): Intent =
        Intent(this, T::class.java).apply {
            if (action != null) setAction(action)
            flags?.forEach { setFlags(it) }
            putExtras(bundleOf(*extras))
        }