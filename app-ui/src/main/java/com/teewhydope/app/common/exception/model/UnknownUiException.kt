package com.teewhydope.app.common.exception.model

import com.teewhydope.app.common.exception.UiException
import com.teewhydope.app.ui.R

class UnknownUiException(throwable: Throwable) : UiException(
    throwable,
    R.string.unknown_exception_dialog_title,
    R.string.unknown_exception_dialog_description
)