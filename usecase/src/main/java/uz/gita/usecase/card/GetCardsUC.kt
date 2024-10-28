package uz.gita.usecase.card

import kotlinx.coroutines.flow.Flow
import uz.gita.common.models.response.CardResponse


/*
    Created by Boburjon Murodov 27/10/24 at 20:49
*/

interface GetCardsUC {
    suspend operator fun invoke(): Flow<Result<CardResponse.GetCardSuccess>>
}