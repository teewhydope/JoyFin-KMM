package com.teewhydope.app.presentation.login.model

import com.teewhydope.app.presentation.common.notification.PresentationNotification

sealed interface LoginPresentationNotification : PresentationNotification {
    data object Authenticated : LoginPresentationNotification
}