package com.teewhydope.app.data.authentication.mapper

import com.teewhydope.app.data.common.mapper.DomainToDataMapper
import com.teewhydope.app.data.authentication.model.LoginWithEmailAndPasswordDataModel
import com.teewhydope.app.domain.authentication.model.LoginWithEmailAndPasswordRequestDomainModel

class LoginRequestDomainToDataModelMapper :
    DomainToDataMapper<LoginWithEmailAndPasswordRequestDomainModel, LoginWithEmailAndPasswordDataModel>() {
    override fun map(input: LoginWithEmailAndPasswordRequestDomainModel) =
        LoginWithEmailAndPasswordDataModel(
            email = input.email,
            password = input.password
        )
}