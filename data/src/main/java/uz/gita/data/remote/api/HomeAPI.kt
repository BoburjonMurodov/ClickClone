package uz.gita.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import uz.gita.common.models.response.HomeResponse


/*
    Created by Boburjon Murodov 27/10/24 at 17:07
*/

internal interface HomeAPI {
    @GET("v1/home/total-balance")
    suspend fun getTotalBalance(@Header("Authorization") token: String): Response<HomeResponse.BalanceResponse>

    @GET("v1/home/user-info")
    suspend fun getBasicInfo(@Header("Authorization") token: String): Response<HomeResponse.BasicInfoResponse>

    @GET("v1/home/user-info/details")
    suspend fun getFullInfo(@Header("Authorization") token: String): Response<HomeResponse.FullInfoResponse>
}