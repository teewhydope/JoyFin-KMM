package com.teewhydope.app.common.extensions

fun Number.formatDecimalSeparator(): String {
    return toString()
        .reversed()
        .chunked(3)
        .joinToString(",")
        .reversed()
}

