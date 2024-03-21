package com.teewhydope.app.common.widgets.input

import android.content.Context
import android.content.res.ColorStateList.valueOf
import android.graphics.Rect
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View.OnFocusChangeListener
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import android.view.inputmethod.EditorInfo.IME_ACTION_NEXT
import android.widget.ImageView
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePaddingRelative
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.teewhydope.app.common.extensions.applyAttributes
import com.teewhydope.app.common.extensions.getColorCompat
import com.teewhydope.app.common.extensions.gone
import com.teewhydope.app.common.extensions.inflate
import com.teewhydope.app.common.extensions.invisible
import com.teewhydope.app.common.extensions.visible
import com.teewhydope.app.common.util.TextValidatorFactory
import com.teewhydope.app.common.util.validation.NO_MINIMUM_CHARACTERS
import com.teewhydope.app.common.util.validation.TextValidator
import com.teewhydope.app.ui.R

private const val NO_VALUE = -1

class InputFieldView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val textInputLayout: TextInputLayout by lazy { findViewById(R.id.text_input_layout) }
    private val textInputField: TextInputEditText by lazy { findViewById(R.id.text_input_edit_text) }
    private val textInputErrorMessage: TextView by lazy { findViewById(R.id.text_input_error_message) }
    private val textInputIcon: ImageView by lazy { findViewById(R.id.text_input_icon) }

    private val textValidatorFactory =
        TextValidatorFactory(resources)

    var state: InputState = InputState.Unselected
        set(value) {
            field = value
            renderState(value)
        }

    private var textValidator: TextValidator = TextValidator.NoValidation(resources)

    var text: String
        get() = textInputField.text.toString().trim()
        set(value) {
            textInputField.setText(value)
            textInputField.setSelection(value.length)
        }

    var inputType: Int = EditorInfo.TYPE_NULL
        set(value) {
            field = value
            textInputField.inputType = value
        }

    var hint: String
        get() = textInputLayout.hint.toString()
        set(value) {
            textInputLayout.hint = value
        }

    private var onEditorActionListener: OnEditorActionListener =
        OnEditorActionListener { _, _, _ -> false }
        set(value) {
            field = value
            textInputField.setOnEditorActionListener(value)
        }

    var onNextOrDoneEditorActionListener: ((Int) -> Unit)? = null
        set(value) {
            field = value
            onEditorActionListener = OnEditorActionListener { _, actionId, _ ->
                if (actionId == IME_ACTION_NEXT || actionId == IME_ACTION_DONE) {
                    value?.invoke(actionId)
                    return@OnEditorActionListener true
                }
                false
            }
        }

    @DrawableRes
    var inputIcon: Int = NO_VALUE
        set(value) {
            field = value
            textInputIcon.setOptionalDrawable(value)
            textInputField.updatePaddingRelative(start = value.toInputPadding())
            textInputErrorMessage.updatePaddingRelative(start = value.toInputPadding())
        }

    override fun requestFocus(direction: Int, previouslyFocusedRect: Rect?): Boolean {
        state = InputState.Selected(text)
        return super.requestFocus(direction, previouslyFocusedRect)
    }

    init {
        inflate(R.layout.text_input_layout, true)
        setAttributes(attrs, defStyleAttr)
        textInputField.id = id
        initListeners()
        renderState(state)
    }

    private fun setAttributes(attributeSet: AttributeSet?, defaultStyle: Int) {
        attributeSet?.applyAttributes(
            context,
            R.styleable.InputFieldView,
            defaultStyle
        ) {
            inputIcon = getResourceId(R.styleable.InputFieldView_input_icon, NO_VALUE)
            inputType = getInt(R.styleable.InputFieldView_android_inputType, EditorInfo.TYPE_NULL)
            getString(R.styleable.InputFieldView_android_hint)?.let { hint ->
                this@InputFieldView.hint = hint
            }
            val minimumCharacters =
                getInt(R.styleable.InputFieldView_input_minimumCharacters, NO_MINIMUM_CHARACTERS)

            textValidator = textValidatorFactory.validator(
                getInt(R.styleable.InputFieldView_input_validation, TextValidatorFactory.NONE),
                minimumCharacters
            )

        }
    }

    fun isInputValid(showErrorView: Boolean = true): Boolean {
        val state = textValidator.validate(textInputField.text?.toString() ?: "")
        if (showErrorView) {
            this.state = state
        }

        return !state.isErrorState
    }

    private fun renderState(state: InputState) {
        handleErrorState(state)
        if (state.text.isBlank()) textInputField.text?.clear()
    }

    private fun handleErrorState(state: InputState) {
        if (state.isErrorState) {
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = (state as? InputState.Error)?.errorMessage
            textInputErrorMessage.text = (state as? InputState.Error)?.errorMessage
            textInputErrorMessage.visible()
        } else {
            textInputLayout.isErrorEnabled = false
            textInputErrorMessage.invisible()
        }

        val hintTextColor = textInputColor(state, R.color.secondary)
        textInputLayout.defaultHintTextColor = valueOf(hintTextColor)

        val textInputColor = textInputColor(state, R.color.primary)
        textInputField.setTextColor(textInputColor)
    }

    private fun textInputColor(state: InputState, @ColorRes defaultColorResourceId: Int) =
        getColorCompat(
            if (state.isErrorState && state.text.isNotBlank()) {
                R.color.red
            } else {
                defaultColorResourceId
            }
        )

    private fun initListeners() {
        textInputField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isEmpty()) {
                    state = state.clearedState()
                    renderState(state)
                }
            }
        })

        textInputField.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            state = if (hasFocus) {
                textInputField.ellipsize = null
                InputState.Selected(textInputField.text?.toString() ?: state.text)
            } else {
                textValidator.validate(textInputField.text?.toString() ?: "")
            }
        }
    }

    private fun ImageView.setOptionalDrawable(@DrawableRes drawableResourceId: Int) =
        if (drawableResourceId == NO_VALUE) {
            gone()
            setImageDrawable(null)
        } else {
            visible()
            setImageResource(drawableResourceId)
        }

    private fun Int.toInputPadding() = if (this == NO_VALUE) {
        resources.getDimension(R.dimen.input_field_padding_without_icon).toInt()
    } else {
        resources.getDimension(R.dimen.input_field_padding_with_icon).toInt()
    }
}