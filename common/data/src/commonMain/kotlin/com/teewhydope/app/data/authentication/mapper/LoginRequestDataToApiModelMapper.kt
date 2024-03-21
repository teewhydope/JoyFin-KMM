package com.teewhydope.app.data.authentication.mapper

import com.teewhydope.app.data.common.mapper.DataToApiMapper
import com.teewhydope.app.data.authentication.model.LoginRequestApiModel
import com.teewhydope.app.data.authentication.model.LoginWithEmailAndPasswordDataModel

class LoginRequestDataToApiModelMapper :
    DataToApiMapper<LoginWithEmailAndPasswordDataModel, LoginRequestApiModel>() {
    override fun map(input: LoginWithEmailAndPasswordDataModel) = LoginRequestApiModel(
        email = input.email,
        password = input.password
    )
}