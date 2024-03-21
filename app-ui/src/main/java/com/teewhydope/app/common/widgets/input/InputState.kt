package com.teewhydope.app.common.widgets.input

import com.teewhydope.app.ui.R

sealed class InputState(
    val color: Int,
    open val text: String
) {

    data object Unselected : InputState(
        R.color.secondary,
        ""
    ) {
        override val isErrorState = false
        override fun clearedState() = Unselected
    }

    data class Selected(override val text: String) : InputState(
        R.color.primary,
        text
    ) {
        override val isErrorState = false
        override fun clearedState() = Selected("")
    }

    data class Finished(override val text: String) : InputState(
        R.color.primary,
        text
    ) {
        override val isErrorState = false
        override fun clearedState() = Unselected
    }

    data class Error(val errorMessage: String, override val text: String) : InputState(
        R.color.red,
        text
    ) {
        override val isErrorState = true
        override fun clearedState() = Unselected
    }

    abstract val isErrorState: Boolean
    abstract fun clearedState(): InputState
}