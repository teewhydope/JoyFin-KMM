package com.teewhydope.app.ui.login.mapper

import androidx.navigation.NavController
import com.teewhydope.app.common.navigation.UiDestination
import com.teewhydope.app.common.navigation.mapper.UiDestinationMapper
import com.teewhydope.app.common.navigation.navigateSafe
import com.teewhydope.app.presentation.common.navigation.PresentationDestination
import com.teewhydope.app.presentation.login.model.LoginPresentationDestination
import com.teewhydope.app.ui.R
import com.teewhydope.app.ui.login.LoginFragmentDirections.Companion.loginFragmentToHomeFragment
import com.teewhydope.app.ui.login.model.LoginResponseUiModel

class LoginPresentationToUiDestinationMapper(
    private val loginResponsePresentationToUiModelMapper: LoginResponsePresentationToUiModelMapper,
    private val globalDestinationMapper: UiDestinationMapper
) : UiDestinationMapper {
    override fun map(presentationDestination: PresentationDestination) =
        when (presentationDestination) {
            is LoginPresentationDestination.LoginSuccessful -> LoginSuccessfulUiDestination(
                cutomerInfo = loginResponsePresentationToUiModelMapper.toUi(presentationDestination.cutomerInfo)
            )

            else -> globalDestinationMapper.map(presentationDestination)
        }

    private data class LoginSuccessfulUiDestination(val cutomerInfo: LoginResponseUiModel) :
        UiDestination {
        override fun navigate(navController: NavController) {
            navController.navigateSafe(loginFragmentToHomeFragment(cutomerInfo))
        }
    }
}