/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.helper.rx

/**
 * Data class for sending event via {@link RxBus} class
 *
 * @author Orkhan Gasimli on 27.02.2018.
 */
data class RxEvent(val type: EventType, val message: String = "")