package uz.gita.boboor.bankingappcompose.presentation.main

import uz.gita.boboor.bankingappcompose.ui.navigation.AppNavigator
import uz.gita.presenter.screens.main.MainScreenContracts
import javax.inject.Inject


/*
    Created by Boburjon Murodov 29/10/24 at 00:49
*/

class MainScreenDirections @Inject constructor(
    private val appNavigator: AppNavigator
) : MainScreenContracts.Directions {
    override suspend fun moveToAddCardScreen() {

    }
}