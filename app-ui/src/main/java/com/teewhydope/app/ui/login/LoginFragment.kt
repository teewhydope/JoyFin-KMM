package com.teewhydope.app.ui.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.teewhydope.app.common.extensions.setVisibility
import com.teewhydope.app.common.fragment.BaseFragment
import com.teewhydope.app.common.util.hideKeyboard
import com.teewhydope.app.common.widgets.input.InputFieldView
import com.teewhydope.app.presentation.login.model.LoginPresentationNotification
import com.teewhydope.app.presentation.login.viewmodel.LoginViewModel
import com.teewhydope.app.presentation.login.viewmodel.LoginViewState
import com.teewhydope.app.ui.R
import com.teewhydope.app.ui.login.mapper.LoginPresentationToUiDestinationMapper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginViewState, LoginPresentationNotification>() {

    override val layout: Int = R.layout.fragment_login

    @Inject
    override lateinit var destinationMapper: LoginPresentationToUiDestinationMapper

    override val viewModel: LoginViewModel by viewModels()

    private val emailInput
        get() = requireView().findViewById<InputFieldView>(R.id.login_email_input)

    private val passwordInput
        get() = requireView().findViewById<InputFieldView>(R.id.login_password_input)

    private val loginButton: View
        get() = requireView().findViewById(R.id.login_button)

    private val loginStatusProgressBar: View
        get() = requireView().findViewById(R.id.login_status_loading_progress_bar)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupInputViews()
    }

    override fun renderViewState(viewState: LoginViewState) {
        super.renderViewState(viewState)
        loginStatusProgressBar.setVisibility(viewState.isLoading)
        loginButton.setVisibility(!viewState.isLoading)
    }

    private fun setupListeners() {
        loginButton.setOnClickListener {
            if (!isInputFieldsValid(listOf(emailInput, passwordInput))) {
                return@setOnClickListener
            }
            viewModel.onLoginAction(
                email = emailInput.text,
                password = passwordInput.text
            )
        }
    }

    private fun setupInputViews() {
        emailInput.onNextOrDoneEditorActionListener = {
            validateInputAndHideKeyboard(emailInput)
        }
        passwordInput.onNextOrDoneEditorActionListener = {
            validateInputAndHideKeyboard(passwordInput)
        }
    }

    private fun isInputFieldsValid(inputViews: List<InputFieldView>): Boolean {
        inputViews.first().hideKeyboard()
        return inputViews.all { it.isInputValid() }
    }

    private fun validateInputAndHideKeyboard(inputView: InputFieldView): Boolean {
        inputView.hideKeyboard()
        return inputView.isInputValid()
    }
}