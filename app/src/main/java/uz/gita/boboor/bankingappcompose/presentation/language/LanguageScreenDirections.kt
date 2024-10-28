package uz.gita.boboor.bankingappcompose.presentation.language

import uz.gita.boboor.bankingappcompose.ui.navigation.AppNavigator
import uz.gita.boboor.bankingappcompose.presentation.login.LoginScreen
import uz.gita.presenter.screens.language.LanguageScreenContract
import javax.inject.Inject

internal class LanguageScreenDirections @Inject constructor(private val appNavigator: AppNavigator) : LanguageScreenContract.Direction {
    override suspend fun moveToSignInScreen() {
        appNavigator.replace(LoginScreen())
    }
}