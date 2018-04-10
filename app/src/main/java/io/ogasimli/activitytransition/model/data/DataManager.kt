/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.model.data

import io.ogasimli.activitytransition.model.data.room.TransitionDao
import io.ogasimli.activitytransition.model.models.TransitionEvent
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Single access point to saving/retrieve data from SharedPreferences, API & DB
 *
 * @author Orkhan Gasimli on 15.02.2018.
 */
@Singleton
class DataManager @Inject constructor(
        val prefsHelper: PreferencesHelper,
        private val transitionDao: TransitionDao) {

    /* ___________________ Transitions ___________________*/

    /**
     * Get all transition data from the table.
     *
     * @return          the list of transitions retrieved from the table
     */
    fun getAllEvents() = transitionDao.getAllEvents()

    /**
     * Get all transition data from the table in reversed chronological order.
     *
     * @return          the list of transitions retrieved from the table
     */
    fun getAllEventsReversed() = transitionDao.getAllEventsReversed()

    /**
     * Get data of a specific transition from the table.
     *
     * @param id        id of the transition to be deleted
     * @return          the transition data from the table
     */
    fun getEventById(id: Long) = transitionDao.getEventById(id)

    /**
     * Get data of a last transition from the table
     *
     * @return          the transition data from the table
     */
    fun getLastEvent() = transitionDao.getLastEvent()

    /**
     * Insert list of transitions to the database.
     * If the transition data already exists, ignore transaction.
     *
     * @param events     the lis of transitions to be inserted.
     * @return          list of id's of inserted drivers
     */
    fun insertEvents(events: List<TransitionEvent>) = transitionDao.insertEvents(events)

    /**
     * Insert a transition to the database.
     * If the transition data already exists, ignore transaction.
     *
     * @param event      the transition to be inserted.
     * @return          list of id's of inserted drivers
     */
    fun insertEvent(vararg event: TransitionEvent) = transitionDao.insertEvent(*event)

    /**
     * Insert list of transitions to the database.
     * If the transition data already exists, replace it.
     *
     * @param events     the lis of transitions to be inserted.
     * @return          list of id's of upserted drivers
     */
    fun upsertEvents(events: List<TransitionEvent>) = transitionDao.upsertEvents(events)

    /**
     * Insert a transition to the database.
     * If the transition data already exists, replace it.
     *
     * @param event      the transition to be inserted.
     * @return          list of id's of upserted drivers
     */
    fun upsertEvent(vararg event: TransitionEvent) = transitionDao.upsertEvent(*event)

    /**
     * Delete all transition data.
     *
     * @return          the number of rows removed from the DB
     */
    fun deleteAllEvents() = transitionDao.deleteAllEvents()

    /**
     * Delete specific transition data.
     *
     * @param event      the transition to be deleted
     * @return          the number of rows removed from the DB
     */
    fun deleteEvent(vararg event: TransitionEvent) = transitionDao.deleteEvent(*event)
}