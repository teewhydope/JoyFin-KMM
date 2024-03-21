package com.teewhydope.app.logger

var _globalLogger = GlobalLogger()

val globalLogger: GlobalLogger by lazy { _globalLogger }

class GlobalLogger(
    private val logger: Logger = DoNothingLogger(),
) : Logger {

    override fun v(message: String) {
        logger.v(message)
    }

    override fun v(throwable: Throwable) {
        logger.v(throwable)
    }

    override fun v(message: String, throwable: Throwable) {
        logger.v(message, throwable)
    }

    override fun e(message: String) {
        logger.e(message)
    }

    override fun e(throwable: Throwable) {
        logger.e(throwable)
    }

    override fun e(message: String, throwable: Throwable) {
        logger.e(message, throwable)
    }

    override fun i(message: String) {
        logger.i(message)
    }

    override fun i(throwable: Throwable) {
        logger.i(throwable)
    }

    override fun i(message: String, throwable: Throwable) {
        logger.i(message, throwable)
    }

    override fun d(message: String) {
        logger.d(message)
    }

    override fun d(throwable: Throwable) {
        logger.d(throwable)
    }

    override fun d(message: String, throwable: Throwable) {
        logger.d(message, throwable)
    }

    override fun w(message: String) {
        logger.w(message)
    }

    override fun w(throwable: Throwable) {
        logger.w((throwable))
    }

    override fun w(message: String, throwable: Throwable) {
        logger.w(message, throwable)
    }
}