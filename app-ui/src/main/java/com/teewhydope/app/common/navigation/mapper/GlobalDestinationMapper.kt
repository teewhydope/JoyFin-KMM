package com.teewhydope.app.common.navigation.mapper

import com.teewhydope.app.common.navigation.AppDestination
import com.teewhydope.app.common.navigation.UiDestination
import com.teewhydope.app.presentation.common.navigation.PresentationDestination

open class GlobalDestinationMapper() : UiDestinationMapper {
    override fun map(presentationDestination: PresentationDestination): UiDestination =
        when (presentationDestination) {
            is PresentationDestination.Back -> AppDestination.Back
            else -> throwInvalidDestinationException(presentationDestination)
        }
}