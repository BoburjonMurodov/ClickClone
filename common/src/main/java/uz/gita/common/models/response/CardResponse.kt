package uz.gita.common.models.response


/*
    Created by Boburjon Murodov 27/10/24 at 18:59
*/


sealed interface CardResponse {
    data class GetCardSuccess(val list: List<GetCardSuccessItem>) : CardResponse

    data class Success(val message: String) : CardResponse
    data class Error(val message: String) : CardResponse
}