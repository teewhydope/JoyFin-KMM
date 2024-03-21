package com.teewhydope.app.common.util.validation

import androidx.annotation.StringRes
import com.teewhydope.app.ui.R

open class PasswordPatternValidator {

    private val lowerCaseRegex = "(.*[a-z].*)".toRegex()
    private val upperCaseRegex = "(.*[A-Z].*)".toRegex()
    private val numberRegex = "(.*[0-9].*)".toRegex()
    private val symbolRegex = "(.*[^A-Za-z0-9].*)".toRegex()

    @StringRes
    val errorResourceId: Int = R.string.input_error_simple_password

    open fun isPasswordValid(password: String): Boolean {
        var matchCount = 0
        if (password.matches(lowerCaseRegex)) {
            matchCount++
        }
        if (password.matches(upperCaseRegex)) {
            matchCount++
        }
        if (password.matches(numberRegex)) {
            matchCount++
        }
        if (password.matches(symbolRegex)) {
            matchCount++
        }
        return matchCount >= 2
    }
}

