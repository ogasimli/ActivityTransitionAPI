/*
 * Copyright (c) 2018 dashroad LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * This file is proprietary and confidential.
 * Written by Orkhan Gasimli <orkhan@dashroad.com> in 2018.
 */

package io.ogasimli.activitytransition.helper.rx

/**
 * Enum class used to indicate the actions send via {@link RxBus}
 *
 * @author Orkhan Gasimli on 27.02.2018.
 */
enum class EventType {
    TRANSITION_UPDATE,
    BACK_PRESS,
    REVERSED_BACK_PRESS,
    START_LOCATION_UPDATE,
    STOP_LOCATION_UPDATE,
    UPDATE_START_BUTTON,
    OPEN_HOME_ACTIVITY,
    OPEN_CAR_SELECT_FRAGMENT,
    OPEN_SIGNUP_FRAGMENT,
    OPEN_HOME_FRAGMENT,
    OPEN_PWD_RESET_CODE_FRAGMENT,
    OPEN_PWD_RESET_FRAGMENT,
    OPEN_LOGIN_FRAGMENT,
    OPEN_CHANGE_CAR_FRAGMENT
}