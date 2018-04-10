/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.view.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.ogasimli.activitytransition.helper.rx.RxBus
import dagger.android.AndroidInjection

/**
 * Base class for all activities.
 * Performs dependency injections
 *
 * @see AndroidInjection
 *
 * @author Orkhan Gasimli on 23.02.2018.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Inject external dependencies
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        // Subscribe to events
        subscribeToEvents()
    }

    /**
     * Base method to subscribe to RxEvents
     */
    open fun subscribeToEvents() {

    }

    override fun onDestroy() {
        // Un-subscribe from bus events
        RxBus.unregister(this)
        super.onDestroy()
    }
}