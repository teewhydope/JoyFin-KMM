package com.teewhydope.app.domain.common.exception

import com.teewhydope.app.domain.common.model.exception.DomainException

data class NoInternetConnectionDomainException(override val throwable: Throwable) :
    DomainException(throwable)