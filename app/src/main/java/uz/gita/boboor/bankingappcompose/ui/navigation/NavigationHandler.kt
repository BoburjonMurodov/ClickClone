package uz.gita.boboor.bankingappcompose.ui.navigation

import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.flow.SharedFlow

typealias NavigationArgs = Navigator.() -> Unit

interface NavigationHandler {
    val screenState: SharedFlow<NavigationArgs>
}