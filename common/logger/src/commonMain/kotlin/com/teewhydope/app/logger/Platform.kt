package com.teewhydope.app.logger

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform