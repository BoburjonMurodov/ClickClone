package uz.gita.data.repository.impl

import uz.gita.common.models.enums.LaunchData
import uz.gita.common.models.enums.PasswordState
import uz.gita.data.local.LocalStorage
import uz.gita.data.repository.AppRepository
import javax.inject.Inject

/*
    Created by Boburjon Murodov 18/10/24 at 17:30
*/

internal class AppRepositoryImpl @Inject constructor(
    private val localStorage: LocalStorage
) : AppRepository {
    override suspend fun getLaunchMode(): Pair<LaunchData, PasswordState> {
        var passwordState = PasswordState.PASSWORD_NOT_SET
        if (localStorage.password.isNotEmpty()) {
            passwordState = PasswordState.PASSWORD_VERIFY
        }
        if (localStorage.verifyPassword.isNotEmpty()) {
            passwordState = PasswordState.PASSWORD_SET
        }

        return if (!localStorage.isSignIn) {
            Pair(LaunchData.UNSIGNED, PasswordState.PASSWORD_NOT_SET)
        } else {
            Pair(LaunchData.PASSWORD, passwordState)
        }
    }

    override suspend fun setSignedInTrue() {
        localStorage.isSignIn = true
    }

    override suspend fun setFirstPassword(password: String) {
        localStorage.password = password
    }

    override suspend fun setVerifyPassword(password: String) {
        localStorage.verifyPassword = password
    }

    override suspend fun getPassword(): String {
        return localStorage.password
    }
}