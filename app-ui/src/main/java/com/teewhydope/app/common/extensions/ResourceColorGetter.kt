package com.teewhydope.app.common.extensions

import android.content.Context
import android.content.res.Resources
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.FontRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView

fun Context.getColorCompat(resourceId: Int) = ContextCompat.getColor(this, resourceId)
fun View.getColorCompat(resourceId: Int) = this.context.getColorCompat(resourceId)
fun Context.getColorStateListCompat(colorRes: Int) = ContextCompat.getColorStateList(this, colorRes)
fun View.getColorStateListCompat(colorRes: Int) = this.context.getColorStateListCompat(colorRes)
fun Resources.getColorCompat(@ColorRes resourceId: Int, theme: Resources.Theme? = null) =
    ResourcesCompat.getColor(this, resourceId, theme)

fun RecyclerView.ViewHolder.getColorCompat(resourceId: Int) = itemView.getColorCompat(resourceId)

fun View.getFontCompat(@FontRes fontId: Int) = ResourcesCompat.getFont(context, fontId)