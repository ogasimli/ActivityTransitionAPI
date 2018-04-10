/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.helper.timber

import android.content.Context
import io.ogasimli.activitytransition.helper.utils.generateInternalFile
import timber.log.Timber
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.*

/**
 * Custom DebugTree class for Timber used for saving logs
 *
 * @author Orkhan Gasimli on 19.02.2018.
 */
class FileLoggingTree constructor(val context: Context) :
        Timber.DebugTree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        try {
            val folderName = "log"
            val fileNameTimeStamp = SimpleDateFormat("dd-MM-yyyy",
                    Locale.getDefault()).format(Date())
            val logTimeStamp = SimpleDateFormat("E MMM dd yyyy 'at' hh:mm:ss:SSS aaa",
                    Locale.getDefault()).format(Date())
            val fileName = "$fileNameTimeStamp.html"

            val file = context.generateInternalFile(folderName, fileName)

            file?.let {
                val writer = FileWriter(file, true)
                writer.use {
                    it.append("<p style=\"background:lightgray;\"><strong style=\"background:lightblue;\">&nbsp&nbsp")
                    it.append(logTimeStamp)
                    it.append(" :&nbsp&nbsp</strong><strong>&nbsp&nbsp")
                    it.append(tag)
                    it.append("</strong> - ")
                    it.append(message)
                    it.append("</p>")
                    it.flush()
                }
            }
        } catch (e: Exception) {
            Timber.e(e, "Error while logging into file.")
        }
    }

    override fun createStackElementTag(element: StackTraceElement): String {
        // Add line numbers to log
        return super.createStackElementTag(element) + " - " + element.lineNumber
    }
}