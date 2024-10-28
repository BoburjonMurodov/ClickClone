package uz.gita.boboor.bankingappcompose.presentation.login

import uz.gita.boboor.bankingappcompose.ui.navigation.AppNavigator
import uz.gita.boboor.bankingappcompose.presentation.register.RegisterScreen
import uz.gita.boboor.bankingappcompose.presentation.signin_verify.SignInVerifyScreen
import uz.gita.presenter.screens.login.LoginScreenContract
import javax.inject.Inject

internal class LoginScreenDirections @Inject constructor(private val appNavigator: AppNavigator) : LoginScreenContract.Directions {
    override suspend fun moveToSignUpScreen() {
        appNavigator.push(RegisterScreen())
    }

    override suspend fun moveToVerifyScreen(phone: String) {
        appNavigator.push(SignInVerifyScreen(phone))
    }
}