package com.teewhydope.app.presentation.login.model

import com.teewhydope.app.domain.authentication.model.LoginResponseDomainModel
import com.teewhydope.app.presentation.common.navigation.PresentationDestination

sealed class LoginPresentationDestination : PresentationDestination {
    data class LoginSuccessful(val cutomerInfo: LoginResponsePresentationModel) :
        LoginPresentationDestination()
}