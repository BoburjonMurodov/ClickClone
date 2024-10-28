package uz.gita.data.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.common.models.request.CardRequest
import uz.gita.common.models.response.CardResponse


/*
    Created by Boburjon Murodov 27/10/24 at 19:17
*/

interface CardRepository {
    fun getCards(): Flow<Result<CardResponse.GetCardSuccess>>
    fun addCard(card: CardRequest.AddCard): Flow<Result<CardResponse.Success>>
    fun deleteCard(id: Int): Flow<Result<CardResponse.Success>>
    fun updateCard(card: CardRequest.UpdateCard): Flow<Result<CardResponse.Success>>
}
