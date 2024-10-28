package uz.gita.usecase.card.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.common.models.request.CardRequest
import uz.gita.common.models.response.CardResponse
import uz.gita.data.repository.CardRepository
import uz.gita.usecase.card.UpdateCardUC
import javax.inject.Inject


/*
    Created by Boburjon Murodov 27/10/24 at 20:51
*/


internal class UpdateCardUCImpl @Inject constructor(private val repository: CardRepository) : UpdateCardUC {
    override suspend fun invoke(card: CardRequest.UpdateCard): Flow<Result<CardResponse.Success>> = repository.updateCard(card)
}