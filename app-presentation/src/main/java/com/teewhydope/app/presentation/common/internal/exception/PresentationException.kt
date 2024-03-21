package com.teewhydope.app.presentation.common.internal.exception

abstract class PresentationException(val throwable: Throwable = Throwable()) : Exception(throwable)