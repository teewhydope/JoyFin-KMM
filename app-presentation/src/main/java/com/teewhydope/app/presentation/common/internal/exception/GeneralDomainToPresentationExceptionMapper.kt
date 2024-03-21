package com.teewhydope.app.presentation.common.internal.exception

import com.teewhydope.app.domain.common.exception.ErrorResponseDomainException
import com.teewhydope.app.domain.common.exception.NoInternetConnectionDomainException
import com.teewhydope.app.domain.common.model.exception.DomainException
import com.teewhydope.app.domain.common.model.exception.UnknownDomainException
import com.teewhydope.app.presentation.common.internal.exception.model.ErrorResponsePresentationException
import com.teewhydope.app.presentation.common.internal.exception.model.NoInternetPresentationException
import com.teewhydope.app.presentation.common.internal.exception.model.UnknownPresentationException
import com.teewhydope.app.presentation.common.mapper.DomainToPresentationMapper

class GeneralDomainToPresentationExceptionMapper :
    DomainToPresentationMapper<DomainException, PresentationException>() {
    override fun map(input: DomainException) = when (input) {
        is UnknownDomainException -> UnknownPresentationException(input)
        is NoInternetConnectionDomainException -> NoInternetPresentationException(input)
        is ErrorResponseDomainException -> ErrorResponsePresentationException(input.throwable)
        else -> UnknownPresentationException(input)
    }
}