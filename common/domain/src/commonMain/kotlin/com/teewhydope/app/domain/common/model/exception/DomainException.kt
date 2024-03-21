package com.teewhydope.app.domain.common.model.exception

abstract class DomainException(open val throwable: Throwable) : Exception(throwable) {
    constructor(message: String) : this(Exception(message))
}