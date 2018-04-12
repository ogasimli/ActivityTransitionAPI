/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.helper.exceptions

import android.content.Context
import io.ogasimli.activitytransition.R

/**
 * Exception classes to be used for app specific purposes
 *
 * @author Orkhan Gasimli on 04.07.2017.
 */

sealed class CustomException(message: String = "") : RuntimeException(message)

class NoDataFoundException : CustomException()

class DbFetchException : CustomException()

/**
 * Extension function to convert error type into string
 *
 * @param context   context object to get string resource
 */
fun CustomException.toMessage(context: Context?): String {
    context?.let {
        return when (this) {
            is NoDataFoundException -> it.getString(R.string.no_data_found_error_msg)
            is DbFetchException -> it.getString(R.string.db_fetch_error_msg)
        }
    }
    return ""
}