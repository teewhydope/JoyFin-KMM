package com.teewhydope.app.presentation.login.viewmodel

import com.teewhydope.app.domain.authentication.model.LoginWithEmailAndPasswordRequestDomainModel
import com.teewhydope.app.domain.authentication.usecase.LoginWithEmailAndPasswordUseCase
import com.teewhydope.app.domain.common.model.exception.DomainException
import com.teewhydope.app.presentation.common.internal.exception.model.ErrorResponsePresentationException
import com.teewhydope.app.presentation.common.viewmodel.BaseViewModel
import com.teewhydope.app.presentation.login.mapper.LoginResponseDomainToPresentationModelMapper
import com.teewhydope.app.presentation.login.model.LoginPresentationDestination.LoginSuccessful
import com.teewhydope.app.presentation.login.model.LoginPresentationNotification
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

const val PASSWORD_LENGTH_NOT_EMPTY = 1

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginResponseDomainToPresentationModelMapper: LoginResponseDomainToPresentationModelMapper,
    private val loginWithEmailAndPasswordUseCase: LoginWithEmailAndPasswordUseCase,
) : BaseViewModel<LoginViewState, LoginPresentationNotification>() {

    override fun initialState() = LoginViewState()

    private fun updateLoadingIndicator(isLoading: Boolean) {
        updateState { lastState ->
            lastState.copy(isLoading = isLoading)
        }
    }

    fun onLoginAction(email: String, password: String) {
        updateLoadingIndicator(true)
        useCaseExecutor.run(
            useCase = loginWithEmailAndPasswordUseCase,
            value = LoginWithEmailAndPasswordRequestDomainModel(
                email = email,
                password = password
            ),
            onResult = { domainModel ->
                updateLoadingIndicator(false)
                navigate(
                    LoginSuccessful(
                        loginResponseDomainToPresentationModelMapper.toPresentation(
                            domainModel
                        )
                    )
                )
            },
            onException = ::handleUseCaseError
        )
    }

    private fun handleUseCaseError(domainException: DomainException) {
        updateLoadingIndicator(false)
        notifyError(ErrorResponsePresentationException(Throwable(domainException.throwable.message)))
    }
}

