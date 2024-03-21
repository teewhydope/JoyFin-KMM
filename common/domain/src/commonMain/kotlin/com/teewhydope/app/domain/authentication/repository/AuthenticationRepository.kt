package com.teewhydope.app.domain.authentication.repository

import com.teewhydope.app.domain.authentication.model.LoginResponseDomainModel
import com.teewhydope.app.domain.authentication.model.LoginWithEmailAndPasswordRequestDomainModel

interface AuthenticationRepository {
    suspend fun login(request: LoginWithEmailAndPasswordRequestDomainModel): LoginResponseDomainModel
}