package com.teewhydope.app.common.util.validation

import android.content.res.Resources
import androidx.annotation.StringRes
import com.teewhydope.app.common.widgets.input.InputState

internal const val NO_MINIMUM_CHARACTERS = -1

abstract class TextValidator(
    internal open val resources: Resources,
    open var minimumCharacters: Int = NO_MINIMUM_CHARACTERS
) {
    internal fun isValidLength(input: String) = minimumCharacters == NO_MINIMUM_CHARACTERS ||
        input.length >= minimumCharacters

    abstract fun validate(input: String): InputState

    fun inputError(@StringRes messageResourceId: Int, input: String): InputState =
        inputError(resources.getString(messageResourceId), input)

    internal fun inputError(message: String, input: String): InputState =
        InputState.Error(
            errorMessage = message,
            text = input
        )

    class NoValidation(resources: Resources) : TextValidator(resources) {
        override fun validate(input: String) = InputState.Finished(input)
    }
}