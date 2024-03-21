package com.teewhydope.app.common.exception.model

import com.teewhydope.app.common.exception.UiException
import com.teewhydope.app.ui.R

class NoInternetUiException(throwable: Throwable) : UiException(
    throwable,
    R.string.no_internet_exception_dialog_title,
    R.string.no_internet_exception_dialog_description
)