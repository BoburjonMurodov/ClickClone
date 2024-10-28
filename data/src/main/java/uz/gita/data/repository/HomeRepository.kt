package uz.gita.data.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.common.models.response.HomeResponse


/*
    Created by Boburjon Murodov 27/10/24 at 17:26
*/

interface HomeRepository {
    suspend fun getTotalBalance(): Flow<Result<HomeResponse.BalanceResponse>>
    suspend fun getBasicInfo(): Flow<Result<HomeResponse.BasicInfoResponse>>
    suspend fun getFullInfo(): Flow<Result<HomeResponse.FullInfoResponse>>
}