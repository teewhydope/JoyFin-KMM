package com.teewhydope.app.common.widgets

import android.graphics.Bitmap
import android.graphics.Canvas
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.view.View

const val SCALE_SIZE = 0.1F
const val BLUR_RADIUS = 5F

fun View.blurBackground(): Bitmap {
    if (width <= 0 || height <= 0) {
        throw RuntimeException("Taking a screenshot of a view $this with height: $height and width: $width")
    }
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val c = Canvas(bitmap)
    draw(c)

    val scaledBitmap = Bitmap.createScaledBitmap(
        bitmap,
        (width * SCALE_SIZE).toInt(),
        (height * SCALE_SIZE).toInt(),
        true
    )

    val outBitmap = Bitmap.createBitmap(
        scaledBitmap.width,
        scaledBitmap.height,
        Bitmap.Config.ARGB_8888
    )
    val rs = RenderScript.create(context)
    val allIn = Allocation.createFromBitmap(rs, scaledBitmap)
    val allOut = Allocation.createFromBitmap(rs, outBitmap)
    ScriptIntrinsicBlur.create(rs, Element.U8_4(rs)).apply {
        setRadius(BLUR_RADIUS)
        setInput(allIn)
        forEach(allOut)
    }
    allOut.copyTo(outBitmap)
    bitmap.recycle()
    scaledBitmap.recycle()
    rs.destroy()

    return outBitmap
}

sealed interface Blur {
    data object Apply : Blur
    data object Clear : Blur
}