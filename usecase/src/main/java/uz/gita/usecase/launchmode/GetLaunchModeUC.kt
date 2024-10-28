package uz.gita.usecase.launchmode

import kotlinx.coroutines.flow.Flow
import uz.gita.common.models.enums.LaunchData
import uz.gita.common.models.enums.PasswordState


/*
    Created by Boburjon Murodov 18/10/24 at 17:35
*/

interface GetLaunchModeUC {
    suspend operator fun invoke(): Flow<Pair<LaunchData, PasswordState>>
}