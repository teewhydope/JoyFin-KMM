package com.teewhydope.app.presentation.login.viewmodel

import com.teewhydope.app.presentation.common.internal.ViewState
import com.teewhydope.app.presentation.login.model.LoginResponsePresentationModel
import com.teewhydope.app.presentation.login.model.UserDataPresentationModel

data class LoginViewState(
    val isLoading: Boolean = false,
    val customerInformation: LoginResponsePresentationModel = LoginResponsePresentationModel(
        smartSaverTransactions = emptyList(),
        userData = UserDataPresentationModel(
            firstName = "",
            smartSaverBalance = 0,
            greenSaverBalance = 0,
            fixedDepositBalance = 0,
        )
    )
) : ViewState
