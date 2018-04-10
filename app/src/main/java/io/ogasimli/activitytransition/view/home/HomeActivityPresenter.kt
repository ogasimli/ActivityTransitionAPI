/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.view.home

import android.content.Context
import android.support.design.widget.FloatingActionButton
import android.support.graphics.drawable.AnimatedVectorDrawableCompat
import io.ogasimli.activitytransition.R
import io.ogasimli.activitytransition.helper.exceptions.DbFetchException
import io.ogasimli.activitytransition.helper.exceptions.NoDataFoundException
import io.ogasimli.activitytransition.model.data.DataManager
import io.ogasimli.activitytransition.model.data.transition.TransitionService
import io.ogasimli.activitytransition.view.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Presenter class serving HomeActivity
 *
 * @author Orkhan Gasimli on 13.02.2018.
 */
class HomeActivityPresenter @Inject constructor(private val dataManager: DataManager) :
        BasePresenter<HomeActivityView>() {

    fun loadData(disposable: Disposable) {
        mvpView?.showProgress()
        (disposable as CompositeDisposable)
                .add(dataManager.getAllEventsReversed()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { transitions ->
                                    Timber.e("Result: $transitions")
                                    mvpView?.hideProgress()

                                    if (transitions == null || transitions.isEmpty()) {
                                        mvpView?.showErrorSnack(NoDataFoundException())
                                    } else {
                                        mvpView?.showResult(transitions)
                                    }
                                },
                                { error ->
                                    Timber.e(error, "Error getting repos:")
                                    mvpView?.hideProgress()
                                    mvpView?.showErrorSnack(DbFetchException())
                                }
                        )
                )
    }

    fun toggleTransitionUpdate() {
        if (TransitionService.isInProgress) mvpView?.stopTransitionService()
        else mvpView?.startTransitionService()
    }

    fun prepareFab(context: Context, fab: FloatingActionButton) {
        val avd = if (TransitionService.isInProgress) {
            AnimatedVectorDrawableCompat.create(context, R.drawable.stop_to_play_anim)
        } else {
            AnimatedVectorDrawableCompat.create(context, R.drawable.play_to_stop_anim)
        }
        fab.setImageDrawable(avd)
    }

    fun animateFab(context: Context, fab: FloatingActionButton) {
        val avd = if (TransitionService.isInProgress) {
            AnimatedVectorDrawableCompat.create(context, R.drawable.play_to_stop_anim)
        } else {
            AnimatedVectorDrawableCompat.create(context, R.drawable.stop_to_play_anim)
        }
        fab.setImageDrawable(avd)

        avd?.start()

        fab.isEnabled = true
    }
}