package uz.gita.data.repository

import uz.gita.common.models.enums.LaunchData
import uz.gita.common.models.enums.PasswordState


/*
    Created by Boburjon Murodov 18/10/24 at 17:25
*/

interface AppRepository {
    suspend fun getLaunchMode(): Pair<LaunchData, PasswordState>
    suspend fun setSignedInTrue()

    suspend fun setFirstPassword(password: String)
    suspend fun setVerifyPassword(password: String)

    suspend fun getPassword(): String
}