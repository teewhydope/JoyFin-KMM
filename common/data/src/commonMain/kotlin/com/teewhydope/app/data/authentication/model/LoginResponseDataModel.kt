package com.teewhydope.app.data.authentication.model

data class LoginResponseDataModel(
    val userData: UserDataModel,
    val smartSaverTransactions: List<SmartSaverTransactionDataModel>
)

data class SmartSaverTransactionDataModel(
    val amount: Long,
    val type: String,
    val narration: String,
    val dateCreated: String
)

data class UserDataModel(
    val id: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val tier: String,
    val phoneNumber: String,
    val smartSaverBalance: Long,
    val greenSaverBalance: Long,
    val fixedDepositBalance: Long,
    val emailVerified: Boolean,
    val phoneVerified: Boolean
)
