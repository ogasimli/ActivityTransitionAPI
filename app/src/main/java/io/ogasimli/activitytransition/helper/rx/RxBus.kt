/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.helper.rx

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

/**
 * A simple event bus built with RxJava
 *
 * @author Orkhan Gasimli on 27.02.2018.
 */
object RxBus {

    /**
     * Used to hold all subscriptions for Bus events and un-subscribe properly when needed.
     */
    private val subscriptionsMap: HashMap<Any, CompositeDisposable?> by lazy {
        HashMap<Any, CompositeDisposable?>()
    }

    /**
     * Avoid using this property directly, exposed only because it's used in inline fun
     */
    val publisher: PublishSubject<Any> = PublishSubject.create<Any>()

    /**
     * Publish the event
     */
    fun publish(event: Any) {
        publisher.onNext(event)
    }

    /**
     * Start listening to events of a specific type.
     * Using ofType we filter only events that match that class type
     *
     * @return          an Observable of type T
     */
    inline fun <reified T : Any> listen(): Observable<T> = publisher.ofType(T::class.java)

    /**
     * Unregisters subscriber from Bus events.
     * Calls un-subscribe method of your subscriptions
     *
     * @param subscriber subscriber to unregister
     */
    fun unregister(subscriber: Any) {
        val compositeSubscription = subscriptionsMap[subscriber]
        if (compositeSubscription == null) {
            Timber.d("Trying to unregister subscriber that wasn't registered")
        } else {
            compositeSubscription.clear()
            subscriptionsMap.remove(subscriber)
        }
    }

    /**
     * Registers subscriber with Bus events.
     *
     * @param subscriber    subscriber to unregister
     * @param disposable    disposable that should be generated and added to the map
     */
    internal fun register(subscriber: Any, disposable: Disposable) {
        var compositeSubscription = subscriptionsMap[subscriber]
        if (compositeSubscription == null) {
            compositeSubscription = CompositeDisposable()
        }
        compositeSubscription.add(disposable)
        subscriptionsMap[subscriber] = compositeSubscription
    }
}

/**
 * Registers the subscription to correctly unregister it later to avoid memory leaks
 *
 * @param subscriber subscriber object that owns this subscription
 */
fun Disposable.registerInBus(subscriber: Any) {
    RxBus.register(subscriber, this)
}