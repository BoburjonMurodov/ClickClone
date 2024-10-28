package uz.gita.presenter.screens.language

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.core.model.screenModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject



internal class LanguageVM @Inject constructor(private val direction: LanguageScreenContract.Direction) :
    LanguageScreenContract.ViewModel {
    override fun onEventDispatcher(intent: LanguageScreenContract.Intent) = intent {
        when (intent) {
            LanguageScreenContract.Intent.CLickUzbekButton -> {
                direction.moveToSignInScreen()

            }
        }
    }

    override val container =
        container<LanguageScreenContract.UIState, LanguageScreenContract.SideEffect>(LanguageScreenContract.UIState())
}
