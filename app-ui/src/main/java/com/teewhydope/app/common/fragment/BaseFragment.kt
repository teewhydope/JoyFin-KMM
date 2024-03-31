package com.teewhydope.app.common.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.teewhydope.app.common.exception.UiException
import com.teewhydope.app.common.mapper.GeneralPresentationToUiExceptionMapper
import com.teewhydope.app.common.mapper.PresentationToUiMapper
import com.teewhydope.app.common.navigation.mapper.UiDestinationMapper
import com.teewhydope.app.common.widgets.dialog.showAlertDialog
import com.teewhydope.app.presentation.common.internal.ViewState
import com.teewhydope.app.presentation.common.internal.exception.PresentationException
import com.teewhydope.app.presentation.common.navigation.PresentationDestination
import com.teewhydope.app.presentation.common.notification.PresentationNotification
import com.teewhydope.app.presentation.common.viewmodel.BaseViewModel
import com.teewhydope.app.ui.R
import kotlinx.coroutines.launch

abstract class BaseFragment<VIEW_STATE : ViewState, NOTIFICATION : PresentationNotification> :
    Fragment() {

    abstract val layout: Int

    protected open val childFragmentManagerResultListeners: Map<String, (Bundle) -> Unit> = mapOf()
    protected open val parentFragmentManagerResultListeners: Map<String, (Bundle) -> Unit> = mapOf()

    internal abstract val destinationMapper: UiDestinationMapper

    internal open val presentationToUiExceptionMapper: PresentationToUiMapper<PresentationException, UiException> =
        GeneralPresentationToUiExceptionMapper()

    internal abstract val viewModel: BaseViewModel<VIEW_STATE, NOTIFICATION>

    val navController: NavController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout, container, false)
    }

    open fun renderViewState(viewState: VIEW_STATE) = Unit

    open fun onFragmentResumed() {
        viewModel.onFragmentResume()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onFragmentCreate()
        (activity as AppCompatActivity).supportActionBar?.title =
            javaClass.simpleName.replace("Fragment", "")
    }

    @CallSuper
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModelInternal()
        viewModel.onFragmentViewCreated()
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        attachFragmentResponseHandlers()
    }

    @CallSuper
    override fun onPause() {
        super.onPause()
        viewModel.onFragmentPause()
        detachFragmentResponseHandlers()
    }

    private fun detachFragmentResponseHandlers() {
        childFragmentManagerResultListeners.forEach {
            childFragmentManager.clearFragmentResultListener(it.key)
        }
        parentFragmentManagerResultListeners.forEach {
            parentFragmentManager.clearFragmentResultListener(it.key)
        }
    }

    private fun attachFragmentResponseHandlers() {
        childFragmentManager.attachFragmentResultListeners(
            this,
            childFragmentManagerResultListeners
        )
        parentFragmentManager.attachFragmentResultListeners(
            this,
            parentFragmentManagerResultListeners
        )
    }

    private fun observeViewModelInternal() {

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.presentationExceptionEvents.collect(::throwException)
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect(::applyViewState)
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.destination.collect(::navigate)
            }
        }
    }

    private fun navigate(destination: PresentationDestination) {
        destinationMapper.map(destination).navigate(navController)
    }

    private fun throwException(exception: PresentationException) {
        notifyError(presentationToUiExceptionMapper.toUi(exception))
    }

    private fun applyViewState(viewState: VIEW_STATE) {
        renderViewState(viewState)
    }

    override fun onStart() {
        viewModel.onFragmentStart()
        super.onStart()
    }

    override fun onStop() {
        viewModel.onFragmentStop()
        super.onStop()
    }

    override fun onDestroyView() {
        viewModel.onFragmentDestroyView()
        super.onDestroyView()
    }

    open fun notifyError(uiException: UiException) {
        //globalLogger.e("notifyError${uiException.localizedMessage(resources)}")
        this.showAlertDialog(
            title = context?.getString(uiException.titleResId),
            message = uiException.localizedMessage(resources),
            positiveButtonTextResourceId = R.string.button_action_ok,
            onPositiveButtonClick = {}
        )
    }

    fun notifyError(presentationException: PresentationException) {
        notifyError(presentationToUiExceptionMapper.toUi(presentationException))
    }

    open fun onAcceptErrorResult() = Unit
}

private fun FragmentManager.attachFragmentResultListeners(
    fragment: Fragment,
    listeners: Map<String, (Bundle) -> Unit>
) {
    listeners.forEach { listenerPair ->
        setFragmentResultListener(listenerPair.key, fragment) { _, bundle ->
            listenerPair.value(bundle)
        }
    }
}