package uz.gita.usecase.home.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.common.models.response.HomeResponse
import uz.gita.data.repository.HomeRepository
import uz.gita.usecase.home.GetTotalBalanceUC
import javax.inject.Inject


/*
    Created by Boburjon Murodov 27/10/24 at 18:38
*/

internal class GetTotalBalanceUCIMpl @Inject constructor(private val repository: HomeRepository) : GetTotalBalanceUC {
    override suspend fun invoke(): Flow<Result<HomeResponse.BalanceResponse>> = repository.getTotalBalance()
}