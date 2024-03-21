package com.teewhydope.app.data.authentication.datasource.remote

import com.teewhydope.app.data.authentication.model.LoginResponseDataModel
import com.teewhydope.app.data.authentication.model.LoginWithEmailAndPasswordDataModel
import com.teewhydope.app.data.authentication.model.UserDataModel

interface AuthenticationRemoteSource {
    suspend fun loginWithEmailAndPassword(request: LoginWithEmailAndPasswordDataModel): LoginResponseDataModel

    suspend fun accountVerificationState(userData: UserDataModel)
}