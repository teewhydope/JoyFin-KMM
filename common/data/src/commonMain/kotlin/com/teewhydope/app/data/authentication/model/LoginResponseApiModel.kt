package com.teewhydope.app.data.authentication.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseApiModel(
    @SerialName("user_data")
    val userData: UserDataApiModel,

    @SerialName("smart_saver_transactions")
    val smartSaverTransactions: List<SmartSaverTransactionApiModel>
)

@Serializable
data class SmartSaverTransactionApiModel(
    val amount: Long,
    val type: String,
    val narration: String,

    @SerialName("date_created")
    val dateCreated: String
)

@Serializable
data class UserDataApiModel(
    val id: String,
    val email: String,

    @SerialName("first_name")
    val firstName: String,

    @SerialName("last_name")
    val lastName: String,

    val tier: String,

    @SerialName("phone_number")
    val phoneNumber: String,

    @SerialName("smart_saver_balance")
    val smartSaverBalance: Long,

    @SerialName("green_saver_balance")
    val greenSaverBalance: Long,

    @SerialName("fixed_deposit_balance")
    val fixedDepositBalance: Long,

    @SerialName("email_verified")
    val emailVerified: Boolean,

    @SerialName("phone_verified")
    val phoneVerified: Boolean
)

