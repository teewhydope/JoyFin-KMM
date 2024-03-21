package com.teewhydope.app.presentation.common.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teewhydope.app.domain.common.model.exception.DomainException
import com.teewhydope.app.domain.common.usecase.UseCaseExecutor
import com.teewhydope.app.logger.globalLogger
import com.teewhydope.app.presentation.common.internal.SingleLiveEvent
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
import kotlinx.coroutines.cancel

abstract class BaseViewModel<VIEW_STATE : ViewState, NOTIFICATION : PresentationNotification> :
    ViewModel() {

    open val generalExceptionMapper:
        DomainToPresentationMapper<in DomainException, out PresentationException> =
        GeneralDomainToPresentationExceptionMapper()

    val navigationCommands = SingleLiveEvent<PresentationDestination>()

    val notificationState = SingleLiveEvent<NotificationState>()
    val presentationExceptionEvents = SingleLiveEvent<PresentationException>()

    private val _viewState = MutableLiveData<VIEW_STATE>().apply { value = initialState() }
    val viewState: LiveData<VIEW_STATE> get() = _viewState

    val dialogEvents = SingleLiveEvent<NOTIFICATION>()
    val useCaseExecutor: UseCaseExecutor = UseCaseExecutor(viewModelScope)

    protected fun navigate(presentationDestination: PresentationDestination) {
        navigationCommands.value = presentationDestination
    }

    protected fun navigateBack() {
        navigationCommands.value = Back
    }

    fun updateState(newViewState: VIEW_STATE) {
        _viewState.value = newViewState
    }

    fun updateState(updatedState: (lastState: VIEW_STATE) -> VIEW_STATE) =
        updateState(updatedState(currentViewState())).also {
            globalLogger.v("Updated state ${updatedState(currentViewState())}")
        }

    fun notifyError(exception: PresentationException) {
        globalLogger.e(exception)
        presentationExceptionEvents.value = exception
    }

    fun notifyError(exception: DomainException) {
        notifyError(generalExceptionMapper.toPresentation(exception))
    }

    fun notify(dialogCommand: NOTIFICATION) {
        dialogEvents.value = dialogCommand
    }

    protected fun notifyLoading(message: String) {
        notificationState.value = Loading(message)
    }

    protected fun notifyFailure(message: String, exception: PresentationException) {
        notificationState.value = Failure(message)
        globalLogger.e(message, exception)
    }

    protected fun notifyFailure(message: String, exception: DomainException) {
        notifyFailure(message, generalExceptionMapper.toPresentation(exception))
    }

    protected fun notifySuccess(message: String) {
        notificationState.value = Success(message)
    }

    protected fun notifyNormal(message: String) {
        notificationState.value = Normal(message)
    }

    fun currentViewState() = viewState.value ?: initialState()

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