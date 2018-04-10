/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.model.data

import android.content.Context
import android.content.SharedPreferences
import io.ogasimli.activitytransition.di.qualifier.ApplicationContext
import io.ogasimli.activitytransition.helper.constants.Constants
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Helper class to work with SharedPreferences
 *
 * @author Orkhan Gasimli on 14.02.2018.
 */
@Singleton
class PreferencesHelper @Inject constructor(@ApplicationContext context: Context) : SharedPreferences {

    private val prefs by lazy {
        context.getSharedPreferences(Constants.DEFAULT_SHARED_PREF_KEY, Context.MODE_PRIVATE)
    }

    fun putBoolean(key: String, value: Boolean) {
        prefs.edit().putBoolean(key, value).apply()
    }

    fun putInt(key: String, value: Int) {
        prefs.edit().putInt(key, value).apply()
    }

    fun putLong(key: String, value: Long) {
        prefs.edit().putLong(key, value).apply()
    }

    fun putFloat(key: String, value: Float) {
        prefs.edit().putFloat(key, value).apply()
    }

    fun putString(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    override fun getBoolean(key: String?, defValue: Boolean): Boolean {
        return prefs.getBoolean(key, defValue)
    }

    override fun getInt(key: String?, defValue: Int): Int {
        return prefs.getInt(key, defValue)
    }

    override fun getLong(key: String?, defValue: Long): Long {
        return prefs.getLong(key, defValue)
    }

    override fun getFloat(key: String?, defValue: Float): Float {
        return prefs.getFloat(key, defValue)
    }

    override fun getString(key: String?, defValue: String?): String {
        return prefs.getString(key, defValue)
    }

    override fun getStringSet(key: String?, defValues: MutableSet<String>?): MutableSet<String> {
        return prefs.getStringSet(key, defValues)
    }

    override fun getAll(): MutableMap<String, *> {
        return prefs.all
    }

    override fun contains(key: String?): Boolean {
        return prefs.contains(key)
    }

    override fun edit(): SharedPreferences.Editor {
        return prefs.edit()
    }

    fun removeData(key: String) {
        prefs.edit().remove(key).apply()
    }

    fun clearData() {
        prefs.edit().clear().apply()
    }

    override fun registerOnSharedPreferenceChangeListener(
            listener: SharedPreferences.OnSharedPreferenceChangeListener?) {
        prefs.registerOnSharedPreferenceChangeListener(listener)
    }

    override fun unregisterOnSharedPreferenceChangeListener(
            listener: SharedPreferences.OnSharedPreferenceChangeListener?) {
        prefs.unregisterOnSharedPreferenceChangeListener(listener)
    }
}