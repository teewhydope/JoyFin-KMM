package com.teewhydope.app.common.navigation.mapper

import com.teewhydope.app.common.navigation.UiDestination
import com.teewhydope.app.presentation.common.navigation.PresentationDestination

interface UiDestinationMapper {
    fun map(presentationDestination: PresentationDestination): UiDestination

    fun throwInvalidDestinationException(currentDestinationClass: PresentationDestination): Nothing =
        throw IllegalArgumentException(
            "Destination ${currentDestinationClass::class.java.name} is not supported. " +
                "Please specify appropriate mapper"
        )
}