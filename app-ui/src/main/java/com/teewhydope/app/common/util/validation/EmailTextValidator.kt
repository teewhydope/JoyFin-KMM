package com.teewhydope.app.common.util.validation

import android.content.res.Resources
import com.teewhydope.app.common.widgets.input.InputState
import com.teewhydope.app.ui.R
import java.util.regex.Pattern

open class EmailTextValidator(
    override val resources: Resources,
    override var minimumCharacters: Int = NO_MINIMUM_CHARACTERS
) : TextValidator(
    resources,
    minimumCharacters
) {
    override fun validate(input: String): InputState {
        val trimmedInput = input.trim()
        return when {
            trimmedInput.isBlank() -> inputError(R.string.input_error_empty, trimmedInput)
            !isValidLength(trimmedInput) -> inputError(R.string.input_error_too_short, trimmedInput)
            emailPattern.matcher(trimmedInput).matches() -> InputState.Finished(trimmedInput)
            else -> inputError(R.string.input_error_wrong_email_pattern, trimmedInput)
        }
    }

    companion object {
        private val emailPattern: Pattern by lazy {
            Pattern.compile(
                "^[_A-Za-z0-9-+]+(\\.[+_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
            )
        }
    }
}