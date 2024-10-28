package uz.gita.usecase.home.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.common.models.response.HomeResponse
import uz.gita.data.repository.HomeRepository
import uz.gita.usecase.home.GetBasicInfoUC
import javax.inject.Inject


/*
    Created by Boburjon Murodov 27/10/24 at 18:40
*/

internal class GetBasicInfoUCImpl @Inject constructor(private val repository: HomeRepository) : GetBasicInfoUC {
    override suspend fun invoke(): Flow<Result<HomeResponse.BasicInfoResponse>> = repository.getBasicInfo()
}