package com.teewhydope.app.data.authentication.mapper

import com.teewhydope.app.data.common.mapper.ApiToDataMapper
import com.teewhydope.app.data.authentication.model.UserDataApiModel
import com.teewhydope.app.data.authentication.model.UserDataModel

class UserDataApiToDataModelMapper : ApiToDataMapper<UserDataApiModel, UserDataModel>() {
    override fun map(input: UserDataApiModel) = UserDataModel(
        id = input.id,
        email = input.email,
        firstName = input.firstName,
        lastName = input.lastName,
        tier = input.tier,
        phoneNumber = input.phoneNumber,
        smartSaverBalance = input.smartSaverBalance,
        greenSaverBalance = input.greenSaverBalance,
        fixedDepositBalance = input.fixedDepositBalance,
        emailVerified = input.emailVerified,
        phoneVerified = input.phoneVerified
    )
}