package com.teewhydope.app.ui.home.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.teewhydope.app.common.extensions.applyAttributes
import com.teewhydope.app.common.extensions.inflate
import com.teewhydope.app.ui.R

class HomeAccountItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val accountNameView: TextView by lazy { findViewById(R.id.account_name_label) }
    private val accountBalanceView: TextView by lazy { findViewById(R.id.account_balance) }

    var accountNameText: String
        get() = accountNameView.text.toString()
        set(value) {
            accountNameView.text = value
        }

    var accountBalanceText: String
        get() = accountBalanceView.text.toString()
        set(value) {
            accountBalanceView.text = value
        }

    init {
        inflate(R.layout.view_home_account_item, true)
        setAttributes(attrs, defStyleAttr)
    }

    private fun setAttributes(attributeSet: AttributeSet?, defaultStyle: Int) {
        attributeSet?.applyAttributes(
            context,
            R.styleable.HomeAccountItemView,
            defaultStyle
        ) {
            accountNameView.text =
                getString(R.styleable.HomeAccountItemView_title) ?: ""
            accountBalanceView.text =
                getString(R.styleable.HomeAccountItemView_subtitle) ?: ""

        }
    }
}