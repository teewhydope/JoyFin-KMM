package com.teewhydope.app.common.extensions

import android.view.View

fun View.visible(): View = this.apply {
    this.visibility = View.VISIBLE
}

fun View.gone(): View = this.apply {
    this.visibility = View.GONE
}

fun View.invisible(): View = this.apply {
    this.visibility = View.INVISIBLE
}

fun View.setVisibility(isVisible: Boolean) = this.apply {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.setInvisibility(isInvisible: Boolean) =
    run { visibility = if (isInvisible) View.INVISIBLE else View.VISIBLE }

fun View.isNotVisible() = visibility != View.VISIBLE

fun View.animateVisibility(isVisible: Boolean, duration: Long = 300): View {
    when (isVisible) {
        true -> fadeIn(duration = duration)
        false -> fadeOut(duration = duration)
    }
    return this
}

fun View.fadeIn(duration: Long = 300, targetAlpha: Float = 1f) {
    if (visibility != View.VISIBLE) alpha = 0f
    animate().alpha(targetAlpha).setDuration(duration).start()
    visibility = View.VISIBLE
}

fun View.fadeOut(duration: Long = 300, targetAlpha: Float = 0f) {
    animate().alpha(targetAlpha).setDuration(duration).start()
    visibility = View.INVISIBLE
}

fun View.showScaleAnimation() {
    animate().scaleX(1f).scaleY(1f)
}