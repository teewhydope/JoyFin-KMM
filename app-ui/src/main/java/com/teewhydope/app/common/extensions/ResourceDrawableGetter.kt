package com.teewhydope.app.common.extensions

import android.content.Context
import android.view.View
import androidx.appcompat.content.res.AppCompatResources

fun Context.getDrawableCompat(resourceId: Int) = AppCompatResources.getDrawable(this, resourceId)
fun View.getDrawableCompat(resourceId: Int) = context.getDrawableCompat(resourceId)