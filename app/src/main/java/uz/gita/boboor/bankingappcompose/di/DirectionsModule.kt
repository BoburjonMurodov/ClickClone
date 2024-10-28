package uz.gita.boboor.bankingappcompose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.boboor.bankingappcompose.presentation.language.LanguageScreenDirections
import uz.gita.boboor.bankingappcompose.presentation.login.LoginScreenDirections
import uz.gita.boboor.bankingappcompose.presentation.pin.PasswordScreenDirections
import uz.gita.boboor.bankingappcompose.presentation.register.RegisterScreenDirections
import uz.gita.boboor.bankingappcompose.presentation.signin_verify.SingInVerifyScreenDirections
import uz.gita.boboor.bankingappcompose.presentation.signup_verify.SingUpVerifyScreenDirections
import uz.gita.boboor.bankingappcompose.presentation.splash.SplashScreenDirections
import uz.gita.presenter.screens.language.LanguageScreenContract
import uz.gita.presenter.screens.login.LoginScreenContract
import uz.gita.presenter.screens.pin.PasswordScreenContract
import uz.gita.presenter.screens.register.RegisterScreenContract
import uz.gita.presenter.screens.signin_verify.SignInVerifyScreenContract
import uz.gita.presenter.screens.singup_verify.SignUpVerifyScreenContract
import uz.gita.presenter.screens.splash.SplashScreenContract


@Module
@InstallIn(SingletonComponent::class)
internal interface DirectionsModule {
    @Binds
    fun bindLanguageScreenDirections(impl: LanguageScreenDirections): LanguageScreenContract.Direction

    @Binds
    fun bindLoginScreenDirections(impl: LoginScreenDirections): LoginScreenContract.Directions

    @Binds
    fun bindRegisterScreenDirections(impl: RegisterScreenDirections): RegisterScreenContract.Directions

    @Binds
    fun bindSingUpScreenDirections(impl: SingUpVerifyScreenDirections): SignUpVerifyScreenContract.Directions


    @Binds
    fun bindSingInScreenDirections(impl: SingInVerifyScreenDirections): SignInVerifyScreenContract.Directions

    @Binds
    fun bindSplashScreenDirections(impl: SplashScreenDirections): SplashScreenContract.Directions

    @Binds
    fun bindPasswordScreenDirections(impl: PasswordScreenDirections): PasswordScreenContract.Directions
}