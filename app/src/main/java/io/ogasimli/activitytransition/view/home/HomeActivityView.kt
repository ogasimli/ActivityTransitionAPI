/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.view.home

import io.ogasimli.activitytransition.helper.exceptions.CustomException
import io.ogasimli.activitytransition.model.models.TransitionEvent
import io.ogasimli.activitytransition.view.base.MvpView

/**
 * MvpView class serving HomeActivity
 *
 * @author Orkhan Gasimli on 13.02.2018.
 */
interface HomeActivityView : MvpView {

    fun startTransitionService()

    fun stopTransitionService()

    fun showProgress()

    fun hideProgress()

    fun showResult(transitions: List<TransitionEvent>)

    fun showErrorSnack(exception: CustomException)
}