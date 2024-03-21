package com.teewhydope.app.common.extensions

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.annotation.StyleableRes

fun AttributeSet.applyAttributes(
    context: Context,
    @StyleableRes attributeIds: IntArray,
    defaultStyledAttributes: Int,
    applyStyledAttributes: TypedArray.() -> Unit
) {
    val attributes =
        context.obtainStyledAttributes(this, attributeIds, defaultStyledAttributes, 0)
    try {
        applyStyledAttributes(attributes)
    } finally {
        attributes.recycle()
    }
}

const val RESOURCE_NO_ID = -1

fun TypedArray.getIdAttribute(styleableResource: Int, defaultValue: Int = RESOURCE_NO_ID) =
    if (hasValue(styleableResource)) {
        getResourceId(styleableResource, defaultValue)
    } else {
        defaultValue
    }

fun TypedArray.getIntAttribute(styleableResource: Int, defaultValue: Int = RESOURCE_NO_ID) =
    if (hasValue(styleableResource)) {
        getInt(styleableResource, defaultValue)
    } else {
        defaultValue
    }

fun TypedArray.getBooleanAttribute(styleableResource: Int, defaultValue: Boolean = false) =
    if (hasValue(styleableResource)) {
        getBoolean(styleableResource, defaultValue)
    } else {
        defaultValue
    }

fun TypedArray.getStringAttribute(styleableResource: Int, defaultValue: String = "") =
    if (hasValue(styleableResource)) {
        val value = getString(styleableResource)
        if (value.isNullOrBlank()) {
            defaultValue
        } else {
            value
        }
    } else {
        defaultValue
    }

fun TypedArray.getColorAttribute(
    @StyleableRes styleableResource: Int,
    @ColorInt defaultValue: Int
) = if (hasValue(styleableResource)) {
    getColor(styleableResource, defaultValue)
} else {
    defaultValue
}

fun TypedArray.getFloatAttribute(styleableResource: Int, defaultValue: Float) =
    if (hasValue(styleableResource)) {
        getFloat(styleableResource, defaultValue)
    } else {
        defaultValue
    }

fun TypedArray.getDimensionAttribute(@StyleableRes styleableResource: Int, defaultValue: Float) =
    if (hasValue(styleableResource)) {
        getDimension(styleableResource, defaultValue)
    } else {
        defaultValue
    }