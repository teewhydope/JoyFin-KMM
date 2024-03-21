package com.teewhydope.app.joyfin.android.di.logger

import com.teewhydope.app.logger.Logger
import com.teewhydope.app.logger.PlatformLogger
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LoggerModule {

    @Provides
    @Reusable
    fun providesPlatformLogger(): Logger = PlatformLogger()
}