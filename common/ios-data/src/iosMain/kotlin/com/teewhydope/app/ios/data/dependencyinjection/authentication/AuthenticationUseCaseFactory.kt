package com.teewhydope.app.ios.data.dependencyinjection.authentication

import com.teewhydope.app.domain.authentication.repository.AuthenticationRepository
import com.teewhydope.app.domain.authentication.usecase.LoginWithEmailAndPasswordUseCase
import com.teewhydope.app.domain.authentication.usecase.LoginWithEmailAndPasswordUseCaseImpl

class AuthenticationUseCaseFactory(
    private val authenticationRepository: AuthenticationRepository,
) {

    val getLoginWithEmailAndPasswordUseCase: LoginWithEmailAndPasswordUseCase by lazy {
        LoginWithEmailAndPasswordUseCaseImpl(
            authenticationRepository = authenticationRepository,
        )
    }
}