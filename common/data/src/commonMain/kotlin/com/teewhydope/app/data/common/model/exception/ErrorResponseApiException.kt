package com.teewhydope.app.data.common.model.exception

class ErrorResponseApiException(message: String, val code: Int) : Exception(message)