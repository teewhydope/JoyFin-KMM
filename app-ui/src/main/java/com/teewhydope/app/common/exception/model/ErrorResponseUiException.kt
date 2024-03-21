package com.teewhydope.app.common.exception.model

import android.content.res.Resources
import com.teewhydope.app.common.exception.UiException
import com.teewhydope.app.ui.R

class ErrorResponseUiException(throwable: Throwable) :
    UiException(throwable, titleResId = R.string.unknown_exception_dialog_title) {
    override fun localizedMessage(resources: Resources) = requireMessage(resources)

    private fun requireMessage(resources: Resources) =
        throwable.message ?: resources.getString(R.string.unknown_error)
}