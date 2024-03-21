package com.teewhydope.app.presentation.common.viewmodel

import com.teewhydope.app.presentation.common.internal.EmptyViewState
import com.teewhydope.app.presentation.common.internal.ViewState
import com.teewhydope.app.presentation.common.notification.PresentationNotification

class DoNothingViewModel :
    BaseViewModel<ViewState, PresentationNotification>() {
    override fun initialState() = EmptyViewState
}