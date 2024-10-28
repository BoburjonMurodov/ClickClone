package uz.gita.data.repository.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.common.models.request.CardRequest
import uz.gita.common.models.response.CardResponse
import uz.gita.data.extensions.flowWithCatch
import uz.gita.data.extensions.toResult
import uz.gita.data.remote.api.CardAPI
import uz.gita.data.repository.CardRepository
import javax.inject.Inject


/*
    Created by Boburjon Murodov 27/10/24 at 19:18
*/

class CardRepositoryImpl @Inject constructor(private val api: CardAPI) : CardRepository {
    override fun getCards(): Flow<Result<CardResponse.GetCardSuccess>> = flowWithCatch {
        emit(api.getCards().toResult { it })
    }

    override fun addCard(card: CardRequest.AddCard): Flow<Result<CardResponse.Success>> = flowWithCatch {
        emit(api.addCard(card).toResult { it })
    }

    override fun deleteCard(id: Int): Flow<Result<CardResponse.Success>> = flowWithCatch {
        emit(api.deleteCard(id).toResult { it })
    }

    override fun updateCard(card: CardRequest.UpdateCard): Flow<Result<CardResponse.Success>> = flowWithCatch {
        emit(api.updateCard(card).toResult { it })
    }
}