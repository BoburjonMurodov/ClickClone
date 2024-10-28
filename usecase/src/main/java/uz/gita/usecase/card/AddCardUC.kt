package uz.gita.usecase.card

import kotlinx.coroutines.flow.Flow
import uz.gita.common.models.request.CardRequest
import uz.gita.common.models.response.CardResponse


/*
    Created by Boburjon Murodov 27/10/24 at 20:49
*/

interface AddCardUC {
    suspend operator fun invoke(card: CardRequest.AddCard): Flow<Result<CardResponse.Success>>
}
