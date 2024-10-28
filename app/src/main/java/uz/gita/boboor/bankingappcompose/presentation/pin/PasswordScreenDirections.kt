package uz.gita.boboor.bankingappcompose.presentation.pin

import uz.gita.boboor.bankingappcompose.presentation.main.MainScreen
import uz.gita.boboor.bankingappcompose.ui.navigation.AppNavigator
import uz.gita.presenter.screens.pin.PasswordScreenContract
import javax.inject.Inject


/*
    Created by Boburjon Murodov 26/10/24 at 20:09
*/

internal class PasswordScreenDirections @Inject constructor(
    private val appNavigator: AppNavigator
) : PasswordScreenContract.Directions {
    override suspend fun moveToMainScreen() {
        appNavigator.replaceAll(MainScreen())
    }
}