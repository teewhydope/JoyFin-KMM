package com.teewhydope.app.data.authentication.datasource.remote

import com.teewhydope.app.data.authentication.mapper.LoginRequestDataToApiModelMapper
import com.teewhydope.app.data.authentication.mapper.LoginResponseApiToDataModelMapper
import com.teewhydope.app.data.authentication.model.LoginResponseDataModel
import com.teewhydope.app.data.authentication.model.LoginWithEmailAndPasswordDataModel
import com.teewhydope.app.data.authentication.model.UserDataModel
import com.teewhydope.app.domain.common.exception.ErrorResponseDomainException

class AuthenticationRemoteDataSource(
    private val authenticationService: AuthenticationService,
    private val loginRequestDataToApiModelMapper: LoginRequestDataToApiModelMapper,
    private val loginResponseApiToDataModelMapper: LoginResponseApiToDataModelMapper
) : AuthenticationRemoteSource {
    override suspend fun loginWithEmailAndPassword(request: LoginWithEmailAndPasswordDataModel): LoginResponseDataModel {
        return if (request.email == "teewhy@mobiledev.com" && request.password == "teewhydope") {
            val requestApiModel = loginRequestDataToApiModelMapper.toApi(request)
            val loginApiModel = authenticationService.loginWithUserPass(requestApiModel)
            loginResponseApiToDataModelMapper.toData(loginApiModel)
        } else {
            throw ErrorResponseDomainException(Exception("Incorrect email or password"), 400)
        }
    }

    override suspend fun accountVerificationState(userData: UserDataModel) {
        if (!userData.emailVerified || !userData.phoneVerified) {
            throw ErrorResponseDomainException(Exception("Your account setup is incomplete."), 400)
        }
    }
}