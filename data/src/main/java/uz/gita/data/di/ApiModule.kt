package uz.gita.data.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.data.local.LocalStorage
import uz.gita.data.remote.api.AuthAPI
import uz.gita.data.remote.api.CardAPI
import uz.gita.data.remote.api.HomeAPI
import uz.gita.data.util.Auth
import uz.gita.data.util.AuthAuthenticator
import uz.gita.data.util.Authenticator
import uz.gita.data.util.Chucker
import uz.gita.data.util.Home
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
internal class ApiModule {
    @Provides
    @Singleton
    @Chucker
    fun provideChuckerInterceptor(@ApplicationContext context: Context): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(ChuckerInterceptor.Builder(context).build())
            .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
            .build()

    @Provides
    @Singleton
    @Authenticator
    fun provideAuthenticatorInterceptor(
        localStorage: LocalStorage,
        @ApplicationContext context: Context,
        authenticator: AuthAuthenticator
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .authenticator(authenticator)
            .addInterceptor {
                val request = it.request().newBuilder()
                if (localStorage.isSignIn) {
                    val token = localStorage.token
                    request.header("Authorization", "Bearer $token")
                }
                val response = it.proceed(request.build())
                if (response.code == 401) {
                    localStorage.isSignIn = false
                    localStorage.password = ""
                    localStorage.verifyPassword = ""
                }
                response
            }
            .addInterceptor(ChuckerInterceptor.Builder(context).build())
            .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
            .build()

    @Auth
    @Provides
    fun provideHomeRetrofit(@Chucker client: OkHttpClient): Retrofit = Retrofit
        .Builder()
        .baseUrl("http://195.158.16.140/mobile-bank/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @Home
    @Provides
    fun provideRetrofit(@Authenticator client: OkHttpClient): Retrofit = Retrofit
        .Builder()
        .baseUrl("http://195.158.16.140/mobile-bank/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @Provides
    @Singleton
    fun provideAuthApi(@Auth retrofit: Retrofit): AuthAPI = retrofit.create(AuthAPI::class.java)

    @Provides
    @Singleton
    fun provideHomeApi(@Home retrofit: Retrofit): HomeAPI = retrofit.create(HomeAPI::class.java)

    @Provides
    @Singleton
    fun provideCardApi(@Home retrofit: Retrofit): CardAPI = retrofit.create(CardAPI::class.java)
}