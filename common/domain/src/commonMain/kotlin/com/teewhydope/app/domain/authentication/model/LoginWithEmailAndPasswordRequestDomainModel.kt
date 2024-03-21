package com.teewhydope.app.domain.authentication.model

data class LoginWithEmailAndPasswordRequestDomainModel(
    val email: String,
    val password: String
)
