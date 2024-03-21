package com.teewhydope.app.logger

interface Logger {
    fun v(message: String)
    fun v(throwable: Throwable)
    fun v(message: String, throwable: Throwable)

    fun e(message: String)
    fun e(throwable: Throwable)
    fun e(message: String, throwable: Throwable)

    fun i(message: String)
    fun i(throwable: Throwable)
    fun i(message: String, throwable: Throwable)

    fun d(message: String)
    fun d(throwable: Throwable)
    fun d(message: String, throwable: Throwable)

    fun w(message: String)
    fun w(throwable: Throwable)
    fun w(message: String, throwable: Throwable)
}