package uz.gita.boboor.bankingappcompose.presentation.signin_verify

import uz.gita.boboor.bankingappcompose.presentation.pin.PasswordScreen
import uz.gita.boboor.bankingappcompose.ui.navigation.AppNavigator
import uz.gita.common.models.enums.PasswordState
import uz.gita.presenter.screens.signin_verify.SignInVerifyScreenContract
import javax.inject.Inject

internal class SingInVerifyScreenDirections @Inject constructor(private val appNavigator: AppNavigator) :
    SignInVerifyScreenContract.Directions {
    override suspend fun moveBack() {
        appNavigator.back()
    }

    override suspend fun moveToPinScreen() {
        appNavigator.push(PasswordScreen(PasswordState.PASSWORD_NOT_SET))
    }
}