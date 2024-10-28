package uz.gita.usecase.card.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.common.models.request.CardRequest
import uz.gita.common.models.response.CardResponse
import uz.gita.data.repository.CardRepository
import uz.gita.usecase.card.AddCardUC
import javax.inject.Inject


/*
    Created by Boburjon Murodov 27/10/24 at 20:51
*/

internal class AddCardUCImpl @Inject constructor(private val repository: CardRepository) : AddCardUC {
    override suspend fun invoke(card: CardRequest.AddCard): Flow<Result<CardResponse.Success>> = repository.addCard(card)
}