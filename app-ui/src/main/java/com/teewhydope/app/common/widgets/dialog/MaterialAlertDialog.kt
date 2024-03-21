package com.teewhydope.app.common.widgets.dialog

import android.app.ActionBar
import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.teewhydope.app.common.extensions.getColorCompat
import com.teewhydope.app.common.widgets.Blur
import com.teewhydope.app.common.widgets.Blur.Apply
import com.teewhydope.app.common.widgets.Blur.Clear
import com.teewhydope.app.common.widgets.blurBackground
import com.teewhydope.app.common.widgets.dialog.AlertDialogPosition.Default
import com.teewhydope.app.ui.R

fun Fragment.showAlertDialog(
    title: String? = null,
    @StringRes titleResourceId: Int? = null,
    message: String? = null,
    @StringRes messageResourceId: Int? = null,
    @StringRes positiveButtonTextResourceId: Int? = R.string.button_action_ok,
    onPositiveButtonClick: (() -> Unit)? = null,
    @ColorRes positiveButtonColorResourceId: Int? = null,
    @StringRes negativeButtonTextResourceId: Int? = null,
    onNegativeButtonClick: (() -> Unit)? = null,
    buttonsAllCaps: Boolean = true,
    dialogPosition: AlertDialogPosition = Default,
    applyBlurred: Boolean = true,
) {
    showAlertDialog(
        requireActivity(),
        requireContext(),
        title,
        titleResourceId,
        message,
        messageResourceId,
        positiveButtonTextResourceId,
        onPositiveButtonClick,
        positiveButtonColorResourceId,
        negativeButtonTextResourceId,
        onNegativeButtonClick,
        buttonsAllCaps,
        dialogPosition,
        applyBlurred
    )
}

private fun showAlertDialog(
    fragmentActivity: FragmentActivity,
    context: Context,
    title: String? = null,
    @StringRes titleResourceId: Int? = null,
    message: String? = null,
    @StringRes messageResourceId: Int? = null,
    @StringRes positiveButtonTextResourceId: Int? = R.string.button_action_ok,
    onPositiveButtonClick: (() -> Unit)? = null,
    @ColorRes positiveButtonColorResourceId: Int? = null,
    @StringRes negativeButtonTextResourceId: Int? = null,
    onNegativeButtonClick: (() -> Unit)? = null,
    buttonsAllCaps: Boolean = true,
    dialogPosition: AlertDialogPosition = Default,
    applyBlurred: Boolean
): AlertDialog {
    val dialogBuilder = MaterialAlertDialogBuilder(context)

    val blur: View by lazy {
        View(context).also { blur ->
            blur.post {
                fragmentActivity.window.decorView.apply {
                    blur.background = BitmapDrawable(
                        resources,
                        blurBackground()
                    )
                }
            }
        }
    }
    setBlur(fragmentActivity, blur, applyBlurred, Apply)

    dialogBuilder.setTitle(title)
    if (titleResourceId != null) dialogBuilder.setTitle(titleResourceId)

    dialogBuilder.setMessage(message)
    if (messageResourceId != null) dialogBuilder.setMessage(messageResourceId)

    positiveButtonTextResourceId?.let { positiveButtonResourceId ->
        dialogBuilder.setPositiveButton(positiveButtonResourceId) { _, _ -> onPositiveButtonClick?.invoke() }
    }

    negativeButtonTextResourceId?.let { negativeButtonResourceId ->
        dialogBuilder.setNegativeButton(negativeButtonResourceId) { _, _ -> onNegativeButtonClick?.invoke() }
    }

    dialogPosition.setInsets(dialogBuilder)

    val dialog = dialogBuilder.show()

    dialog.setDialogLayout(context.resources)

    dialogPosition.setPosition(dialog)

    dialog.getButton(AlertDialog.BUTTON_POSITIVE).isAllCaps = buttonsAllCaps
    dialog.getButton(AlertDialog.BUTTON_NEGATIVE).isAllCaps = buttonsAllCaps

    positiveButtonColorResourceId?.let { buttonColor ->
        dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            .setTextColor(context.getColorCompat(buttonColor))
    }
    dialog.setOnDismissListener {
        setBlur(fragmentActivity, blur, applyBlurred, Clear)
    }
    return dialog
}

fun Dialog.setDialogLayout(resources: Resources) {
    window?.setLayout(
        resources.getDimensionPixelSize(R.dimen.material_dialog_icon_width),
        ActionBar.LayoutParams.WRAP_CONTENT
    )
}

private fun setBlur(
    fragmentActivity: FragmentActivity,
    blurView: View,
    applyBlurred: Boolean,
    blur: Blur
) {
    if (applyBlurred) {
        when (blur) {
            Apply -> (fragmentActivity.window?.decorView as? ViewGroup)?.addView(blurView)
            Clear -> (fragmentActivity.window?.decorView as? ViewGroup)?.removeView(blurView)
        }
    }
}

sealed class AlertDialogPosition {
    abstract fun setPosition(alertDialog: AlertDialog)
    abstract fun setInsets(materialAlertDialogBuilder: MaterialAlertDialogBuilder)

    data object Default : AlertDialogPosition() {
        override fun setPosition(alertDialog: AlertDialog) = Unit
        override fun setInsets(materialAlertDialogBuilder: MaterialAlertDialogBuilder) = Unit
    }

    data object Top : AlertDialogPosition() {
        override fun setPosition(alertDialog: AlertDialog) {
            alertDialog.window?.apply {
                setGravity(Gravity.TOP)
            }
        }

        override fun setInsets(materialAlertDialogBuilder: MaterialAlertDialogBuilder) {
            materialAlertDialogBuilder.setBackgroundInsetTop(0)
        }
    }
}

