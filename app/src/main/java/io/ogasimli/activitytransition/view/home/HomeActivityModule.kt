/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.view.home

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides
import io.ogasimli.activitytransition.di.qualifier.ApplicationContext
import io.ogasimli.activitytransition.helper.utils.intentFor
import io.ogasimli.activitytransition.model.data.transition.TransitionService
import io.ogasimli.activitytransition.view.home.adapter.TransitionsAdapter
import io.reactivex.disposables.CompositeDisposable

/**
 * DI Module providing dependencies for HomeActivity
 *
 * @author Orkhan Gasimli on 13.02.2018.
 */
@Module
class HomeActivityModule {

    /**
     * Provide intent to LocationService
     */
    @Provides
    fun provideTransitionServiceIntent(@ApplicationContext context: Context): Intent =
            context.intentFor<TransitionService>()


    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()

    @Provides
    fun provideLinearLayoutManager(homeActivity: HomeActivity) = LinearLayoutManager(homeActivity)

    @Provides
    fun provideTransitionsAdapter() = TransitionsAdapter()
}