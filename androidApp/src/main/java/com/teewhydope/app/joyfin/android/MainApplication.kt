package com.teewhydope.app.joyfin.android

import android.app.Application
import com.teewhydope.app.logger.GlobalLogger
import com.teewhydope.app.logger.Logger
import com.teewhydope.app.logger._globalLogger
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application() {
    @Inject
    lateinit var logger: Logger

    override fun onCreate() {
        super.onCreate()

        _globalLogger = GlobalLogger(logger)
    }
}