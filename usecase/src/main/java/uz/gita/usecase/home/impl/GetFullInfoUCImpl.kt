package uz.gita.usecase.home.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.common.models.response.HomeResponse
import uz.gita.data.repository.HomeRepository
import uz.gita.usecase.home.GetFullInfoUC
import javax.inject.Inject


/*
    Created by Boburjon Murodov 27/10/24 at 18:43
*/

internal class GetFullInfoUCImpl @Inject constructor(private val repository: HomeRepository) : GetFullInfoUC {
    override suspend fun invoke(): Flow<Result<HomeResponse.FullInfoResponse>> = repository.getFullInfo()
}