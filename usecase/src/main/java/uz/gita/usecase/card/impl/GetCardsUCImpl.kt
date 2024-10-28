package uz.gita.usecase.card.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.common.models.response.CardResponse
import uz.gita.data.repository.CardRepository
import uz.gita.usecase.card.GetCardsUC
import javax.inject.Inject


/*
    Created by Boburjon Murodov 27/10/24 at 20:50
*/

internal class GetCardsUCImpl @Inject constructor(private val repository: CardRepository) : GetCardsUC {
    override suspend fun invoke(): Flow<Result<CardResponse.GetCardSuccess>> = repository.getCards()
}