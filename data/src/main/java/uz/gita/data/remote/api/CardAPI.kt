package uz.gita.data.remote.api

import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import uz.gita.common.models.request.CardRequest
import uz.gita.common.models.response.CardResponse


/*
    Created by Boburjon Murodov 27/10/24 at 19:09
*/

interface CardAPI {
    @GET("v1/card")
    suspend fun getCards(): Response<CardResponse.GetCardSuccess>

    @POST("v1/card")
    suspend fun addCard(card: CardRequest.AddCard): Response<CardResponse.Success>


    @PUT("v1/card")
    suspend fun updateCard(card: CardRequest.UpdateCard): Response<CardResponse.Success>


    @DELETE("v1/card/{id}")
    suspend fun deleteCard(@Path("id") id: Int): Response<CardResponse.Success>
}