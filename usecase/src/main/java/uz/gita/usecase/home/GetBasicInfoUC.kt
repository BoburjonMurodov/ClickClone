package uz.gita.usecase.home

import kotlinx.coroutines.flow.Flow
import uz.gita.common.models.response.HomeResponse


/*
    Created by Boburjon Murodov 27/10/24 at 18:39
*/

interface GetBasicInfoUC {
    suspend operator fun invoke(): Flow<Result<HomeResponse.BasicInfoResponse>>
}