package com.teewhydope.app.common.util.validation

import android.content.res.Resources
import com.teewhydope.app.common.widgets.input.InputState.Finished
import com.teewhydope.app.ui.R
import java.nio.charset.StandardCharsets

open class PasswordTextValidator(
    override val resources: Resources,
    private val patternValidator: PasswordPatternValidator,
    override var minimumCharacters: Int = NO_MINIMUM_CHARACTERS,
) : TextValidator(resources, minimumCharacters) {
    override fun validate(input: String) = when {
        input.isBlank() -> inputError(R.string.input_error_empty, input)
        !isValidLength(input) -> {
            inputError(
                resources.getString(R.string.input_error_minimum_x_characters, minimumCharacters),
                input
            )
        }

//        !patternValidator.isPasswordValid(input) -> inputError(
//            patternValidator.errorResourceId,
//            input
//        )

        canEncode(input) -> Finished(input)
        else -> inputError(R.string.input_error_invalid_password, input)
    }

    private fun canEncode(input: String) = StandardCharsets.US_ASCII.newEncoder().canEncode(input)
}