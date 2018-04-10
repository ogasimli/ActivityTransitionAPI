/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.di.module

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import io.ogasimli.activitytransition.di.qualifier.ApplicationContext
import io.ogasimli.activitytransition.model.data.room.AppDatabase
import io.ogasimli.activitytransition.model.data.room.TransitionDao
import javax.inject.Singleton

/**
 * DI Module providing app-level dependencies for Room DB
 *
 * @author Orkhan Gasimli on 14.02.2018.
 */
@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "Transitions.db").build()

    @Singleton
    @Provides
    fun provideDriverDao(appDatabase: AppDatabase): TransitionDao = appDatabase.getTransitionDao()
}