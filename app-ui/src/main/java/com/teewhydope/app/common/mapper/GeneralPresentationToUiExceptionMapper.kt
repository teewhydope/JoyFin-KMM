package com.teewhydope.app.common.mapper

import com.teewhydope.app.common.exception.UiException
import com.teewhydope.app.common.exception.model.ErrorResponseUiException
import com.teewhydope.app.common.exception.model.NoInternetUiException
import com.teewhydope.app.common.exception.model.UnknownUiException
import com.teewhydope.app.presentation.common.internal.exception.PresentationException
import com.teewhydope.app.presentation.common.internal.exception.model.ErrorResponsePresentationException
import com.teewhydope.app.presentation.common.internal.exception.model.NoInternetPresentationException

class GeneralPresentationToUiExceptionMapper :
    PresentationToUiMapper<PresentationException, UiException>() {
    override fun map(input: PresentationException): UiException = when (input) {
        is NoInternetPresentationException -> NoInternetUiException(input.throwable)
        is ErrorResponsePresentationException -> ErrorResponseUiException(input.throwable)
        else -> UnknownUiException(input.throwable)
    }
}