package com.teewhydope.app.presentation.login.mapper

import com.teewhydope.app.domain.authentication.model.UserDataDomainModel
import com.teewhydope.app.presentation.common.mapper.DomainToPresentationMapper
import com.teewhydope.app.presentation.login.model.UserDataPresentationModel

class UserDataDomainToPresentationModelMapper :
    DomainToPresentationMapper<UserDataDomainModel, UserDataPresentationModel>() {
    override fun map(input: UserDataDomainModel) = UserDataPresentationModel(
        firstName = input.firstName,
        smartSaverBalance = input.smartSaverBalance,
        greenSaverBalance = input.greenSaverBalance,
        fixedDepositBalance = input.fixedDepositBalance,
    )
}