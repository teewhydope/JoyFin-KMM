package com.teewhydope.app.presentation.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teewhydope.app.domain.common.model.exception.DomainException
import com.teewhydope.app.domain.common.usecase.UseCaseExecutor
import com.teewhydope.app.logger.globalLogger
import com.teewhydope.app.presentation.common.internal.ViewState
import com.teewhydope.app.presentation.common.internal.exception.GeneralDomainToPresentationExceptionMapper
import com.teewhydope.app.presentation.common.internal.exception.PresentationException
import com.teewhydope.app.presentation.common.mapper.DomainToPresentationMapper
import com.teewhydope.app.presentation.common.navigation.PresentationDestination
import com.teewhydope.app.presentation.common.navigation.PresentationDestination.Back
import com.teewhydope.app.presentation.common.notification.PresentationNotification
import com.teewhydope.app.presentation.common.viewmodel.NotificationState.Failure
import com.teewhydope.app.presentation.common.viewmodel.NotificationState.Loading
import com.teewhydope.app.presentation.common.viewmodel.NotificationState.Normal
import com.teewhydope.app.presentation.common.viewmodel.NotificationState.Success
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<VIEW_STATE : ViewState, NOTIFICATION : PresentationNotification> :
    ViewModel() {

    open val generalExceptionMapper:
        DomainToPresentationMapper<in DomainException, out PresentationException> =
        GeneralDomainToPresentationExceptionMapper()

    private val _destination by mutableSharedFlow<PresentationDestination>()
    val destination by immutableFlow { _destination }

    private val _notificationState by mutableSharedFlow<NotificationState>()
    val notificationState by immutableFlow { _notificationState }

    private val _presentationExceptionEvents by mutableSharedFlow<PresentationException>()
    val presentationExceptionEvents by immutableFlow { _presentationExceptionEvents }

    private val _viewState by mutableStateFlow { initialState() }
    val viewState by immutableFlow { _viewState }

    private val _dialogEvents by mutableSharedFlow<NOTIFICATION>()
    val dialogEvents by immutableFlow { _dialogEvents }

    val useCaseExecutor: UseCaseExecutor = UseCaseExecutor(viewModelScope)

    protected fun navigate(presentationDestination: PresentationDestination) {
        MainScope().launch {
            _destination.emit(presentationDestination)
        }
    }

    protected fun navigateBack() {
        MainScope().launch {
            _destination.emit(Back)
        }
    }

    fun updateState(newViewState: VIEW_STATE) {
        _viewState.value = newViewState
    }

    fun updateState(updatedState: (lastState: VIEW_STATE) -> VIEW_STATE) =
        updateState(updatedState(currentViewState)).also {
            globalLogger.v("Updated state ${updatedState(currentViewState)}")
        }

    fun notifyError(exception: PresentationException) {
        globalLogger.e(exception)
        MainScope().launch {
            _presentationExceptionEvents.emit(exception)
        }
    }

    fun notifyError(exception: DomainException) {
        notifyError(generalExceptionMapper.toPresentation(exception))
    }

    fun notify(dialogCommand: NOTIFICATION) {
        MainScope().launch {
            _dialogEvents.emit(dialogCommand)
        }
    }

    protected fun notifyLoading(message: String) {
        MainScope().launch {
            _notificationState.emit(Loading(message))
        }
    }

    protected fun notifyFailure(message: String, exception: PresentationException) {
        MainScope().launch {
            _notificationState.emit(Failure(message))
        }
        globalLogger.e(message, exception)
    }

    protected fun notifyFailure(message: String, exception: DomainException) {
        notifyFailure(message, generalExceptionMapper.toPresentation(exception))
    }

    protected fun notifySuccess(message: String) {
        MainScope().launch {
            _notificationState.emit(Success(message))
        }
    }

    protected fun notifyNormal(message: String) {
        MainScope().launch {
            _notificationState.emit(Normal(message))
        }
    }

    private fun <T> mutableStateFlow(initialValueProvider: () -> T) =
        lazy { MutableStateFlow(initialValueProvider()) }

    private fun <T> mutableSharedFlow() = lazy { MutableSharedFlow<T>() }

    private fun <T, FLOW : MutableSharedFlow<T>> immutableFlow(
        initializer: () -> FLOW
    ): Lazy<Flow<T>> = lazy { initializer() }

    val currentViewState = _viewState.value

    open fun onFragmentCreate() = Unit
    open fun onFragmentViewCreated() = Unit
    open fun onFragmentResume() = Unit
    open fun onFragmentDestroyView() = Unit
    open fun onFragmentPause() = Unit
    open fun onFragmentStop() = Unit
    open fun onFragmentStart() = Unit

    abstract fun initialState(): VIEW_STATE

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel("Scope cleared")
    }
}

sealed class NotificationState(open val message: String) {
    data class Success(override val message: String) : NotificationState(message)
    data class Loading(override val message: String) : NotificationState(message)
    data class Failure(override val message: String) : NotificationState(message)
    data class Normal(override val message: String) : NotificationState(message)
}