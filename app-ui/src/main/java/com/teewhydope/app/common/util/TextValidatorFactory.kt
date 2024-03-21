package com.teewhydope.app.common.util

import android.content.res.Resources
import com.teewhydope.app.common.util.validation.EmailTextValidator
import com.teewhydope.app.common.util.validation.NO_MINIMUM_CHARACTERS
import com.teewhydope.app.common.util.validation.PasswordPatternValidator
import com.teewhydope.app.common.util.validation.PasswordTextValidator
import com.teewhydope.app.common.util.validation.TextValidator

class TextValidatorFactory(private val resources: Resources) {
    fun validator(type: Int, minimumCharacters: Int = NO_MINIMUM_CHARACTERS) = when (type) {
        NONE -> TextValidator.NoValidation(resources)
        EMAIL -> EmailTextValidator(resources, minimumCharacters)
        PASSWORD -> PasswordTextValidator(resources, PasswordPatternValidator(), minimumCharacters)
        else -> throw IllegalArgumentException("Type $type is unsupported")
    }

    companion object {
        const val NONE = 0
        private const val EMAIL = 1
        private const val PASSWORD = 2
    }
}