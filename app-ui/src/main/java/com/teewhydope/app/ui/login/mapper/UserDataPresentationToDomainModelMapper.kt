package com.teewhydope.app.ui.login.mapper

import com.teewhydope.app.common.mapper.PresentationToUiMapper
import com.teewhydope.app.presentation.login.model.UserDataPresentationModel
import com.teewhydope.app.ui.login.model.UserDataUiModel

class UserDataPresentationToDomainModelMapper :
    PresentationToUiMapper<UserDataPresentationModel, UserDataUiModel>() {
    override fun map(input: UserDataPresentationModel) = UserDataUiModel(
        firstName = input.firstName,
        smartSaverBalance = input.smartSaverBalance,
        greenSaverBalance = input.greenSaverBalance,
        fixedDepositBalance = input.fixedDepositBalance,
    )
}