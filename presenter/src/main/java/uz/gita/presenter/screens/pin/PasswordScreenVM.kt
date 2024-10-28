package uz.gita.presenter.screens.pin

import android.content.Context
import cafe.adriel.voyager.core.model.screenModelScope
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import uz.gita.common.models.enums.PasswordState
import uz.gita.usecase.launchmode.CheckPasswordUC
import uz.gita.usecase.launchmode.SetFirstPasswordUC
import uz.gita.usecase.launchmode.SetVerifyPasswordUC
import javax.inject.Inject


/*
    Created by Boburjon Murodov 19/10/24 at 02:00
*/


class PasswordScreenVM @Inject constructor(
    private val setFirstPasswordUC: SetFirstPasswordUC,
    private val setVerifyPassword: SetVerifyPasswordUC,
    private val checkPasswordUC: CheckPasswordUC,
    private val directions: PasswordScreenContract.Directions,
    @ApplicationContext private val context: Context
) : PasswordScreenContract.ViewModel {

    override fun onEventDispatcher(intent: PasswordScreenContract.Intent) = intent {
        when (intent) {
            PasswordScreenContract.Intent.ClickBackspace -> {
                if (state.showError != PasswordScreenContract.Error.NONE) reduce { state.copy(showError = PasswordScreenContract.Error.NONE) }

                val temp = mutableListOf<Int>()
                temp.addAll(state.password)
                for (i in temp.size - 1 downTo 0) {
                    if (temp[i] != -1) {
                        temp[i] = -1
                        break
                    }
                }
                reduce { state.copy(password = temp) }
            }

            is PasswordScreenContract.Intent.ClickPassword -> {
                if (state.password.contains(-1)) {
                    val temp = mutableListOf<Int>()
                    temp.addAll(state.password)
                    for (i in temp.indices) {
                        if (temp[i] == -1) {
                            temp[i] = intent.pass
                            break
                        }
                    }
                    reduce { state.copy(password = temp) }
                }

                if (!state.password.contains(-1)) {
                    delay(300)
                    when (state.passwordState
                    ) {
                        PasswordState.PASSWORD_NOT_SET -> {
                            setFirstPasswordUC.invoke(getPasswordAsString(state.password))
                            reduce {
                                state.copy(
                                    password = mutableListOf(-1, -1, -1, -1, -1),
                                    text = "Click-Pinni tasdiqlang",
                                    passwordState = PasswordState.PASSWORD_VERIFY
                                )
                            }
                        }

                        PasswordState.PASSWORD_VERIFY -> {
                            val password = getPasswordAsString(state.password)
                            checkPasswordUC.invoke(password)
                                .onEach {
                                    if (it) {
                                        setVerifyPassword.invoke(password)
                                        directions.moveToMainScreen()
                                    } else {
                                        reduce { state.copy(showError = PasswordScreenContract.Error.WRONG_VERIFY) }
                                    }
                                }
                                .launchIn(screenModelScope)
                        }


                        PasswordState.PASSWORD_SET -> {
                            checkPasswordUC.invoke(getPasswordAsString(state.password))
                                .onEach {
                                    if (it) {
                                        directions.moveToMainScreen()
                                    } else {
                                        reduce { state.copy(showError = PasswordScreenContract.Error.WRONG_PASSWORD) }
                                    }
                                }
                                .launchIn(screenModelScope)
                        }
                    }
                }


            }
        }
    }


    override fun init(passwordState: PasswordState) = intent {
        reduce { state.copy(passwordState = passwordState) }
    }

    private fun getPasswordAsString(password: MutableList<Int>): String {
        val stringBuilder = StringBuilder("")

        password.forEach {
            stringBuilder.append(it)
        }

        return stringBuilder.toString()
    }

    override val container = container<PasswordScreenContract.UIState, Nothing>(PasswordScreenContract.UIState())
}


