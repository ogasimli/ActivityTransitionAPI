/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import io.ogasimli.activitytransition.di.qualifier.ApplicationContext

/**
 * DI Module providing app-level dependencies
 *
 * @author Orkhan Gasimli on 13.02.2018.
 */
@Module(subcomponents = [] /* Add additional sub components here */)
class AppModule {

    /**
     * Provide app context
     */
    @Provides
    @ApplicationContext
    fun provideAppContext(app: Application): Context = app
}