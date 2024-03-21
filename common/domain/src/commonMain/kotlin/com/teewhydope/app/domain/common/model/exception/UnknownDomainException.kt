package com.teewhydope.app.domain.common.model.exception

data class UnknownDomainException(override val throwable: Throwable) : DomainException(throwable)