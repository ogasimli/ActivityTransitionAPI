/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.helper.timber

import android.os.Build
import android.util.Log

import timber.log.Timber

/**
 * Custom DebugTree class for Timber used by debug builds
 *
 * @author Orkhan Gasimli on 08.08.2017.
 */
class DebugLogTree : Timber.DebugTree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        var logPriority = priority
        // Workaround vor Huawei devices not showing logs
        if (Build.MANUFACTURER == "HUAWEI" || Build.MANUFACTURER == "samsung") {
            if (logPriority == Log.VERBOSE || logPriority == Log.DEBUG || logPriority == Log.INFO)
                logPriority = Log.ERROR
        }
        super.log(logPriority, tag, message, t)
    }

    override fun createStackElementTag(element: StackTraceElement): String {
        // Add line numbers to log
        return super.createStackElementTag(element) + " - " + element.lineNumber
    }
}
