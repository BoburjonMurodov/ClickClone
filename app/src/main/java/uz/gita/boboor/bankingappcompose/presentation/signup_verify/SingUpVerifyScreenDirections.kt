package uz.gita.boboor.bankingappcompose.presentation.signup_verify

import uz.gita.boboor.bankingappcompose.presentation.pin.PasswordScreen
import uz.gita.boboor.bankingappcompose.ui.navigation.AppNavigator
import uz.gita.common.models.enums.PasswordState
import uz.gita.presenter.screens.singup_verify.SignUpVerifyScreenContract
import javax.inject.Inject

internal class SingUpVerifyScreenDirections @Inject constructor(private val appNavigator: AppNavigator) :
    SignUpVerifyScreenContract.Directions {
    override suspend fun moveBack() {
        appNavigator.back()
    }

    override suspend fun moveToPinScreen() {
        appNavigator.push(PasswordScreen(PasswordState.PASSWORD_NOT_SET))
    }
}