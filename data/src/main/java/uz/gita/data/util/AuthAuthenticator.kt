package uz.gita.data.util

import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import uz.gita.data.local.LocalStorage
import uz.gita.data.remote.api.AuthAPI
import uz.gita.data.remote.request.AuthRequestModel
import uz.gita.data.repository.AuthRepository
import javax.inject.Inject

/**
 * Created by Sherzodbek Muhammadiev on 05/09/24.
 */


internal class AuthAuthenticator @Inject constructor(
    private val localStorage: LocalStorage,
    private val oAuthApi: AuthAPI,
) : Authenticator {

    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
        const val TOKEN_TYPE = "Bearer"
    }

    override fun authenticate(route: Route?, response: Response): Request? {
        val currentToken = runBlocking { localStorage.accessToken }
        synchronized(this) {
            val updatedToken = runBlocking { localStorage.accessToken }
            val token = if (currentToken != updatedToken) updatedToken else {
                val newSessionResponse = runBlocking { oAuthApi.updateToken(AuthRequestModel.UpdateToken(localStorage.refreshToken)) }
                if (newSessionResponse.isSuccessful && newSessionResponse.body() != null) {
                    newSessionResponse.body()?.let { body ->
                        runBlocking {
                            localStorage.accessToken = body.accessToken
                            localStorage.refreshToken = body.refreshToken
                        }
                        body.accessToken
                    }
                } else null
            }
            if (token == null) {
                runBlocking {
//                    TODO()
                }
            }
            return if (token != null) response.request.newBuilder()
                .header(HEADER_AUTHORIZATION, "$TOKEN_TYPE $token")
                .build() else null
        }
    }
}