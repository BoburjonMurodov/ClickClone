package uz.gita.usecase.launchmode.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.common.models.enums.LaunchData
import uz.gita.common.models.enums.PasswordState
import uz.gita.data.repository.AppRepository
import uz.gita.usecase.launchmode.GetLaunchModeUC
import javax.inject.Inject


/*
    Created by Boburjon Murodov 18/10/24 at 17:36
*/

internal class GetLaunchModeUCImpl @Inject constructor(private val repository: AppRepository) : GetLaunchModeUC {
    override suspend fun invoke(): Flow<Pair<LaunchData, PasswordState>> = flow {
        emit(repository.getLaunchMode())
    }
}