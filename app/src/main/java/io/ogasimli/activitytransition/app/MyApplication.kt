/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.app

import android.app.Activity
import android.app.Application
import android.app.Service
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import io.ogasimli.activitytransition.BuildConfig
import io.ogasimli.activitytransition.di.component.DaggerAppComponent
import io.ogasimli.activitytransition.helper.timber.DebugLogTree
import io.ogasimli.activitytransition.helper.timber.FileLoggingTree
import io.ogasimli.activitytransition.helper.timber.ReleaseLogTree
import timber.log.Timber
import javax.inject.Inject

/**
 * Application class
 *
 * @author Orkhan Gasimli on 12.02.2018.
 */
class MyApplication : Application(), HasActivityInjector, HasServiceInjector {

    @Inject
    lateinit var serviceInjector: DispatchingAndroidInjector<Service>

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }

    override fun serviceInjector(): AndroidInjector<Service> {
        return serviceInjector
    }

    override fun onCreate() {
        super.onCreate()

        // Inject Dagger
        injectDagger()

        // Plant Timber
        plantTimber()
    }

    /**
     * Helper method to inject DaggerAppComponent
     */
    private fun injectDagger() {
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }

    /**
     * Helper method to plan Timber logging trees.
     *
     * If app is in debug mode, then plant {@link DebugLogTree} & {@link FileLoggingTree},
     * else plant {@link ReleaseLogTree}
     */
    private fun plantTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugLogTree())
            val fileLoggingPlanted = Timber.forest().any { it is FileLoggingTree }
            if (!fileLoggingPlanted) Timber.plant(FileLoggingTree(this))
        } else {
            Timber.plant(ReleaseLogTree())
        }
    }
}