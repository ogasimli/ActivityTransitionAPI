/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.ogasimli.activitytransition.model.data.transition.TransitionIntentService
import io.ogasimli.activitytransition.model.data.transition.TransitionIntentServiceModule
import io.ogasimli.activitytransition.model.data.transition.TransitionService
import io.ogasimli.activitytransition.model.data.transition.TransitionServiceModule

/**
 * DI Module used for injection of Services
 *
 * @author Orkhan Gasimli on 13.02.2018.
 */
@Module
abstract class ServiceBuilder {

    @ContributesAndroidInjector(modules = [
        TransitionServiceModule::class])
    abstract fun provideTransitionService(): TransitionService

    @ContributesAndroidInjector(modules = [
        TransitionIntentServiceModule::class])
    abstract fun provideTransitionIntentService(): TransitionIntentService
}