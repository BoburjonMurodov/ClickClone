package uz.gita.boboor.bankingappcompose.presentation.splash

import uz.gita.boboor.bankingappcompose.presentation.login.LoginScreen
import uz.gita.boboor.bankingappcompose.presentation.pin.PasswordScreen
import uz.gita.boboor.bankingappcompose.ui.navigation.AppNavigator
import uz.gita.common.models.enums.PasswordState
import uz.gita.presenter.screens.splash.SplashScreenContract
import javax.inject.Inject


/*
    Created by Boburjon Murodov 19/10/24 at 01:46
*/

internal class SplashScreenDirections @Inject constructor(private val appNavigator: AppNavigator) :
    SplashScreenContract.Directions {
    override suspend fun moveToSignInScreen() {
        appNavigator.replace(LoginScreen())
    }

    override suspend fun moveToPasswordScreen(passwordState: PasswordState) {
        appNavigator.replace(PasswordScreen(passwordState))
    }
}