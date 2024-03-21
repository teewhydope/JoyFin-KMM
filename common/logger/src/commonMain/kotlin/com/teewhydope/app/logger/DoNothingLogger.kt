package com.teewhydope.app.logger

class DoNothingLogger : Logger {
    override fun v(message: String) = Unit

    override fun v(throwable: Throwable) = Unit

    override fun v(message: String, throwable: Throwable) = Unit

    override fun e(message: String) = Unit

    override fun e(throwable: Throwable) = Unit

    override fun e(message: String, throwable: Throwable) = Unit

    override fun i(message: String) = Unit

    override fun i(throwable: Throwable) = Unit

    override fun i(message: String, throwable: Throwable) = Unit

    override fun d(message: String) = Unit

    override fun d(throwable: Throwable) = Unit

    override fun d(message: String, throwable: Throwable) = Unit

    override fun w(message: String) = Unit

    override fun w(throwable: Throwable) = Unit

    override fun w(message: String, throwable: Throwable) = Unit
}