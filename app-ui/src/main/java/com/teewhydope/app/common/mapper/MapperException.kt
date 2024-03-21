package com.teewhydope.app.common.mapper

class PresentationMapperException(message: String, throwable: Throwable? = null) :
    Exception(message, throwable)

class UiMapperException(message: String, throwable: Throwable?) : Exception(message, throwable)