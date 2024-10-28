package uz.gita.boboor.bankingappcompose.presentation.add_card

import uz.gita.boboor.bankingappcompose.ui.navigation.AppNavigator
import uz.gita.presenter.screens.add_card.AddCardContract
import javax.inject.Inject

/*
 * Created by mrgladiator on 10/29/24
 */

class AddCardDirection @Inject constructor(private val appNavigator: AppNavigator) : AddCardContract.Direction {
    override suspend fun moveToAddCardVerify() {

    }
}