/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.view.home

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import io.ogasimli.activitytransition.R
import io.ogasimli.activitytransition.helper.exceptions.CustomException
import io.ogasimli.activitytransition.helper.exceptions.toMessage
import io.ogasimli.activitytransition.helper.rx.EventType
import io.ogasimli.activitytransition.helper.rx.RxBus
import io.ogasimli.activitytransition.helper.rx.RxEvent
import io.ogasimli.activitytransition.helper.rx.registerInBus
import io.ogasimli.activitytransition.helper.utils.startServiceForeground
import io.ogasimli.activitytransition.model.models.TransitionEvent
import io.ogasimli.activitytransition.view.base.BaseActivity
import io.ogasimli.activitytransition.view.home.adapter.TransitionsAdapter
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.indeterminateProgressDialog
import javax.inject.Inject

/**
 * Home screen activity
 *
 * @author Orkhan Gasimli on 20.02.2018.
 */
class HomeActivity : BaseActivity(), HomeActivityView {

    @Inject
    lateinit var presenter: HomeActivityPresenter

    @Inject
    lateinit var transitionServiceIntent: Intent

    @Inject
    lateinit var disposable: CompositeDisposable

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var transitionsAdapter: TransitionsAdapter

    private val progressDialog by lazy {
        indeterminateProgressDialog(
                message = R.string.progress_dialog_msg,
                title = R.string.extract_progress_dialog_title) {
            setCancelable(false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set content layout
        setContentView(R.layout.activity_main)

        // Attach presenter
        presenter.attachView(this)

        presenter.prepareFab(this, fab)

        fab.setOnClickListener {
            presenter.toggleTransitionUpdate()
        }

        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        presenter.loadData(disposable)
    }

    override fun onPause() {
        // Clear disposable
        disposable.clear()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Detach presenter
        presenter.detachView()
    }

    /**
     * Helper function to listen for RxEvent and register subscriber within the pool
     */
    override fun subscribeToEvents() {
        RxBus.listen<RxEvent>()
                .subscribe { event ->
                    when (event.type) {
                        EventType.TRANSITION_UPDATE -> presenter.animateFab(this, fab)
                    }
                }
                .registerInBus(this)
    }

    private fun setupRecyclerView() {
        // Setup RecyclerView
        recycler_view?.apply {
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = transitionsAdapter
            layoutManager = linearLayoutManager
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0 && fab?.visibility == View.VISIBLE) {
                        fab?.hide()
                    } else if (dy < 0 && fab?.visibility != View.VISIBLE) {
                        fab?.show()
                    }
                }
            })
        }
    }

    /* MvpView methods */
    override fun showProgress() {
        progressDialog.show()
    }

    override fun hideProgress() {
        progressDialog.dismiss()
    }

    override fun startTransitionService() {
        // Start service, taking into account Android version
        startServiceForeground(transitionServiceIntent)
        // Disable fab
        fab.isEnabled = false
    }

    override fun stopTransitionService() {
        stopService(transitionServiceIntent)
        // Disable fab
        fab.isEnabled = false
    }

    override fun showResult(transitions: List<TransitionEvent>) {
        transitionsAdapter.transitionItems = transitions
    }

    override fun showErrorSnack(exception: CustomException) {
        val msg = exception.toMessage(this)
        val snackbar = Snackbar.make(home_coordinator, msg, Snackbar.LENGTH_LONG)
        snackbar.setAction(android.R.string.ok) {
            snackbar.dismiss()
        }
        snackbar.show()
    }
}
