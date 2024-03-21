package com.teewhydope.app.domain.common.exception

import com.teewhydope.app.domain.common.model.exception.DomainException

class ErrorResponseDomainException(throwable: Throwable, val errorCode: Int) :
    DomainException(throwable)