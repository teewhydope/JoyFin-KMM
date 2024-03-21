package com.teewhydope.app.presentation.common.internal.exception.model

import com.teewhydope.app.presentation.common.internal.exception.PresentationException

class UnknownPresentationException(throwable: Throwable) : PresentationException(throwable)