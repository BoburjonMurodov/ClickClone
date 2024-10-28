package uz.gita.presenter.screens.splash

import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import uz.gita.common.models.enums.LaunchData
import uz.gita.usecase.launchmode.GetLaunchModeUC
import javax.inject.Inject


/*
    Created by Boburjon Murodov 19/10/24 at 01:36
*/

internal class SplashScreenVM @Inject constructor(
    private val directions: SplashScreenContract.Directions,
    private val getLaunchModeUC: GetLaunchModeUC,
) : SplashScreenContract.ViewModel {

    override fun onEventDispatcher(intent: SplashScreenContract.Intent) = intent {
        when (intent) {
            SplashScreenContract.Intent.GetLaunchMode -> {
                getLaunchModeUC.invoke()
                    .onEach {
                        delay(1000)
                        when (it.first) {
                            LaunchData.PASSWORD -> directions.moveToPasswordScreen(it.second)
                            LaunchData.UNSIGNED -> directions.moveToSignInScreen()
                        }
                    }
                    .launchIn(screenModelScope)
            }
        }
    }

    override val container = container<SplashScreenContract.UIState, Nothing>(SplashScreenContract.UIState())
}