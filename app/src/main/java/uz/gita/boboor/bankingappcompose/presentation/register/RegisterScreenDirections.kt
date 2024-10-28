package uz.gita.boboor.bankingappcompose.presentation.register

import uz.gita.boboor.bankingappcompose.presentation.signup_verify.SignUpVerifyScreen
import uz.gita.boboor.bankingappcompose.ui.navigation.AppNavigator
import uz.gita.presenter.screens.register.RegisterScreenContract
import javax.inject.Inject

internal class RegisterScreenDirections @Inject constructor(private val appNavigator: AppNavigator) : RegisterScreenContract.Directions {
    override suspend fun moveToVerifyScreen(phone: String) {
        appNavigator.push(SignUpVerifyScreen(phone))
    }

    override suspend fun backToSignInScreen() {
        appNavigator.back()
    }
}