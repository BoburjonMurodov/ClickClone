package uz.gita.usecase.card.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.common.models.response.CardResponse
import uz.gita.data.repository.CardRepository
import uz.gita.usecase.card.DeleteCardUC
import javax.inject.Inject


/*
    Created by Boburjon Murodov 27/10/24 at 20:51
*/


internal class DeleteCardUCImpl @Inject constructor(private val repository: CardRepository) : DeleteCardUC {
    override suspend fun invoke(id: Int): Flow<Result<CardResponse.Success>> = repository.deleteCard(id)
}