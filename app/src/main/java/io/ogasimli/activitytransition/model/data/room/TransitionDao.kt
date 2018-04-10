/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.model.data.room

import android.arch.persistence.room.*
import io.ogasimli.activitytransition.model.models.TransitionEvent
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Interface defining necessary methods for manipulating transitions table.
 *
 * @author Orkhan Gasimli on 14.02.2018.
 */
@Dao
interface TransitionDao {

    /**
     * Get all transition data from the table.
     *
     * @return          the list of transitions retrieved from the table
     */
    @Query("SELECT * FROM transitions")
    fun getAllEvents(): Flowable<List<TransitionEvent>>

    /**
     * Get all transition data from the table in reversed chronological order.
     *
     * @return          the list of transitions retrieved from the table
     */
    @Query("SELECT * FROM transitions ORDER BY eventTimeMillis DESC")
    fun getAllEventsReversed(): Flowable<List<TransitionEvent>>

    /**
     * Get data of a specific transition from the table.
     *
     * @param id        id of the transition to be deleted
     * @return          the transition data from the table
     */
    @Query("SELECT * FROM transitions WHERE id=:id LIMIT 1")
    fun getEventById(id: Long): Single<TransitionEvent>

    /**
     * Get data of a last transition from the table
     *
     * @return          the transition data from the table
     */
    @Query("SELECT * FROM transitions ORDER BY eventTimeMillis DESC LIMIT 1")
    fun getLastEvent(): Flowable<List<TransitionEvent>>

    /**
     * Insert list of transitions to the database.
     * If the transition data already exists, ignore transaction.
     *
     * @param events     the lis of transitions to be inserted.
     * @return          list of id's of inserted drivers
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertEvents(events: List<TransitionEvent>): LongArray

    /**
     * Insert a transition to the database.
     * If the transition data already exists, ignore transaction.
     *
     * @param event      the transition to be inserted.
     * @return          list of id's of inserted drivers
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertEvent(vararg event: TransitionEvent): LongArray

    /**
     * Insert list of transitions to the database.
     * If the transition data already exists, replace it.
     *
     * @param events     the lis of transitions to be inserted.
     * @return          list of id's of upserted drivers
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertEvents(events: List<TransitionEvent>): LongArray

    /**
     * Insert a transition to the database.
     * If the transition data already exists, replace it.
     *
     * @param event      the transition to be inserted.
     * @return          list of id's of upserted drivers
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertEvent(vararg event: TransitionEvent): LongArray

    /**
     * Delete all transition data.
     *
     * @return          the number of rows removed from the DB
     */
    @Query("DELETE FROM transitions")
    fun deleteAllEvents(): Int

    /**
     * Delete specific transition data.
     *
     * @param event      the transition to be deleted
     * @return          the number of rows removed from the DB
     */
    @Delete
    fun deleteEvent(vararg event: TransitionEvent): Int
}