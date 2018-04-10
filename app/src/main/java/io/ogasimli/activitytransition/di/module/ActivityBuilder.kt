/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.ogasimli.activitytransition.view.home.HomeActivity
import io.ogasimli.activitytransition.view.home.HomeActivityModule

/**
 * DI Module used for injection of Activities
 *
 * @author Orkhan Gasimli on 13.02.2018.
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    abstract fun provideHomeActivity(): HomeActivity
}