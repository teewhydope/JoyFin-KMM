package com.teewhydope.app.joyfin.android.di.authentication

import com.teewhydope.app.domain.authentication.repository.AuthenticationRepository
import com.teewhydope.app.domain.authentication.usecase.LoginWithEmailAndPasswordUseCase
import com.teewhydope.app.domain.authentication.usecase.LoginWithEmailAndPasswordUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationDomainModule {

    @Provides
    @Reusable
    fun providesGetCustomerInformationUseCase(
        authenticationRepository: AuthenticationRepository,
    ): LoginWithEmailAndPasswordUseCase = LoginWithEmailAndPasswordUseCaseImpl(
        authenticationRepository,
    )
}