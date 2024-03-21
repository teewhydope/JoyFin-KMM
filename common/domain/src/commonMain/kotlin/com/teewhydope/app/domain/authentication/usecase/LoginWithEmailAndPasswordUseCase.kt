package com.teewhydope.app.domain.authentication.usecase

import com.teewhydope.app.domain.common.usecase.BackgroundExecutingUseCase
import com.teewhydope.app.domain.common.usecase.BaseUseCase
import com.teewhydope.app.domain.authentication.model.LoginResponseDomainModel
import com.teewhydope.app.domain.authentication.model.LoginWithEmailAndPasswordRequestDomainModel
import com.teewhydope.app.domain.authentication.repository.AuthenticationRepository
import kotlinx.coroutines.CoroutineScope

interface LoginWithEmailAndPasswordUseCase :
    BaseUseCase<LoginWithEmailAndPasswordRequestDomainModel, LoginResponseDomainModel>

class LoginWithEmailAndPasswordUseCaseImpl(
    private val authenticationRepository: AuthenticationRepository
) : LoginWithEmailAndPasswordUseCase,
    BackgroundExecutingUseCase<LoginWithEmailAndPasswordRequestDomainModel, LoginResponseDomainModel>() {
    override suspend fun executeInBackground(
        request: LoginWithEmailAndPasswordRequestDomainModel,
        coroutineScope: CoroutineScope
    ): LoginResponseDomainModel = authenticationRepository.login(request)
}