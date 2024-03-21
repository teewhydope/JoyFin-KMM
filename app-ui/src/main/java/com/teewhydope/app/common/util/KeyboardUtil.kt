package com.teewhydope.app.common.util

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager

private val keyboardHandler = Handler(Looper.getMainLooper())

private const val KEYBOARD_UPDATE_DELAY_MILLISECONDS = 200L

fun View.showKeyboard() {
    keyboardHandler.removeCallbacksAndMessages(null)
    keyboardHandler.postDelayed(
        { showKeyboardImmediate() },
        KEYBOARD_UPDATE_DELAY_MILLISECONDS
    )
}

private fun View.showKeyboardImmediate() {
    if (requestFocus()) {
        val inputMethodManager =
            context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun View.hideKeyboard() {
    keyboardHandler.removeCallbacksAndMessages(null)
    keyboardHandler.postDelayed(
        { hideKeyboardImmediate() },
        KEYBOARD_UPDATE_DELAY_MILLISECONDS
    )
}

private fun View.hideKeyboardImmediate() {
    val inputMethodManager =
        context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.setKeyboardVisibility(isVisible: Boolean) {
    if (isVisible) {
        showKeyboard()
    } else {
        hideKeyboard()
    }
}