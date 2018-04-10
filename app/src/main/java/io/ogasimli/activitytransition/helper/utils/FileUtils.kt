/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.helper.utils

import android.content.Context
import android.os.Environment
import timber.log.Timber
import java.io.File

/**
 * Extension functions for creating, saving & editing files
 *
 * @author Orkhan Gasimli on 25.12.2017.
 */

/**
 * Helper method to determine if external storage is available
 *
 * @return                  return if external storage is available
 */
fun isExternalStorageAvailable(): Boolean {
    return Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()
}

/**
 * Extension function to get access to file or generate it if not exists
 *
 * @param folderName        the firstName of the folder
 * @param fileName          the firstName of the file
 * @return                  created or accessed file
 */
fun generateExternalFile(folderName: String, fileName: String): File? {
    var file: File? = null
    if (isExternalStorageAvailable()) {
        // Access folder
        val root = File(Environment.getExternalStorageDirectory(), folderName)
        // Try to create the folder if it doesn't exist
        if (root.exists() || root.mkdirs()) {
            file = File(root, fileName)
        }
    }
    return file
}

/**
 * Extension function to get access to file or generate it if not exists
 *
 * @param folderName        the firstName of the folder
 * @param fileName          the firstName of the file
 * @return                  created or accessed file
 */
fun Context.generateInternalFile(folderName: String, fileName: String): File? {
    var file: File? = null
    try {
        // Access folder
        val root = File(this.filesDir, folderName)

        // Try to create the folder if it doesn't exist
        if (root.exists() || root.mkdirs()) {
            file = File(root, fileName)
        }

    } catch (e: Exception) {
        Timber.e(e, "Could not create file!")
    }
    return file
}
