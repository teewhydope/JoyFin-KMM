package com.teewhydope.app.common.exception

import android.content.res.Resources
import android.view.View
import androidx.annotation.StringRes

abstract class UiException(
    val throwable: Throwable = Throwable(),
    @StringRes open val titleResId: Int = View.NO_ID,
    @StringRes open val messageResId: Int = View.NO_ID
) : Exception(throwable) {
    open fun localizedMessage(resources: Resources) = resources.getString(messageResId)
}