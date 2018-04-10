/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.view.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import io.ogasimli.activitytransition.R
import io.ogasimli.activitytransition.helper.utils.*
import io.ogasimli.activitytransition.model.models.TransitionEvent
import kotlinx.android.synthetic.main.transition_item.view.*

/**
 * RecyclerView adapter for transitions
 *
 * @author Orkhan Gasimli on 23.02.2018.
 */
class TransitionsAdapter : RecyclerView.Adapter<TransitionsAdapter.ViewHolder>() {

    var transitionItems: List<TransitionEvent> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = parent.inflate(R.layout.transition_item)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Bind items
        holder.bindItem(transitionItems[position])
    }

    override fun getItemCount() = transitionItems.size

    class ViewHolder(view: View?) :
            RecyclerView.ViewHolder(view) {

        fun bindItem(item: TransitionEvent?) {
            // Check if items are dummy
            if (item != null) {
                with(itemView) {
                    activity_txt.text = item.stringify()
                    activity_txt.setCompoundDrawablesWithIntrinsicBounds(getDrawable(context,
                            item.toDrawable()), null, null, null)
                    time_txt.text = convertToDateTime(item.eventTimeMillis)
                }
            }
        }
    }
}
