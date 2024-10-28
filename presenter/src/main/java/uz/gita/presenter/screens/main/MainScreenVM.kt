package uz.gita.presenter.screens.main

import org.orbitmvi.orbit.syntax.simple.intent
import javax.inject.Inject


/*
    Created by Boburjon Murodov 29/10/24 at 00:51
*/

internal class MainScreenVM @Inject constructor(
    private val directions: MainScreenContracts.Directions,
) : MainScreenContracts.ViewModel {
    override fun onEventDispatcher(intent: MainScreenContracts.Intent) = intent {
        when (intent) {
            MainScreenContracts.Intent.OnCLickAddCard -> directions.moveToAddCardScreen()
        }
    }

    override val container = container<MainScreenContracts.UIState, Nothing>(MainScreenContracts.UIState())
}