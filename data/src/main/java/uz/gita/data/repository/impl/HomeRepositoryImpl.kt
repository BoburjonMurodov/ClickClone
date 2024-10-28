package uz.gita.data.repository.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.data.extensions.flowWithCatch
import uz.gita.data.extensions.toResult
import uz.gita.data.local.LocalStorage
import uz.gita.data.remote.api.HomeAPI
import uz.gita.common.models.response.HomeResponse
import uz.gita.data.repository.HomeRepository
import javax.inject.Inject

/*
    Created by Boburjon Murodov 27/10/24 at 17:26
*/



internal class HomeRepositoryImpl @Inject constructor(
    private val api: HomeAPI,
    private val localStorage: LocalStorage
) : HomeRepository {

    override suspend fun getTotalBalance(): Flow<Result<HomeResponse.BalanceResponse>> = flowWithCatch {
        val token = "Bearer ${localStorage.accessToken}"
        emit(api.getTotalBalance(token).toResult { it })
    }

    override suspend fun getBasicInfo(): Flow<Result<HomeResponse.BasicInfoResponse>> = flowWithCatch {
        val token = "Bearer ${localStorage.accessToken}"
        emit(api.getBasicInfo(token).toResult { it })
    }

    override suspend fun getFullInfo(): Flow<Result<HomeResponse.FullInfoResponse>> = flowWithCatch {
        val token = "Bearer ${localStorage.accessToken}"
        emit(api.getFullInfo(token).toResult { it })
    }
}