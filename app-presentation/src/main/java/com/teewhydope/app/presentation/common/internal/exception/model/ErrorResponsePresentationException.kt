package com.teewhydope.app.presentation.common.internal.exception.model

import com.teewhydope.app.presentation.common.internal.exception.PresentationException

class ErrorResponsePresentationException(throwable: Throwable) : PresentationException(throwable)