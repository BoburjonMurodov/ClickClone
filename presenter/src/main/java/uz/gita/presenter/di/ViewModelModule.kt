package uz.gita.presenter.di

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.hilt.ScreenModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap
import uz.gita.presenter.screens.add_card.AddCardViewModel
import uz.gita.presenter.screens.language.LanguageVM
import uz.gita.presenter.screens.login.LoginScreenVM
import uz.gita.presenter.screens.main.MainScreenVM
import uz.gita.presenter.screens.pin.PasswordScreenVM
import uz.gita.presenter.screens.register.RegisterScreenVM
import uz.gita.presenter.screens.signin_verify.SignInVerifyVM
import uz.gita.presenter.screens.singup_verify.SingUpScreenVerifyVM
import uz.gita.presenter.screens.splash.SplashScreenVM


/*
    Created by Boburjon Murodov 17/10/24 at 18:51
*/



@Module
@InstallIn(ActivityComponent::class)

internal interface ViewModelModule {
    @IntoMap
    @Binds
    @ScreenModelKey(LanguageVM::class)
    fun bindLanguageViewModel(impl: LanguageVM): ScreenModel

    @IntoMap
    @Binds
    @ScreenModelKey(LoginScreenVM::class)
    fun bindLoginViewModel(impl: LoginScreenVM): ScreenModel

    @IntoMap
    @Binds
    @ScreenModelKey(RegisterScreenVM::class)
    fun bindRegisterViewModel(impl: RegisterScreenVM): ScreenModel


    @IntoMap
    @Binds
    @ScreenModelKey(SingUpScreenVerifyVM::class)
    fun bindSingUpVerifyViewModel(impl: SingUpScreenVerifyVM): ScreenModel

    @IntoMap
    @Binds
    @ScreenModelKey(SignInVerifyVM::class)
    fun bindSignInVerifyViewModel(impl: SignInVerifyVM): ScreenModel

    @IntoMap
    @Binds
    @ScreenModelKey(SplashScreenVM::class)
    fun bindSplashScreenViewModel(impl: SplashScreenVM): ScreenModel

    @IntoMap
    @Binds
    @ScreenModelKey(PasswordScreenVM::class)
    fun bindPasswordScreenViewModel(impl: PasswordScreenVM): ScreenModel

    @IntoMap
    @Binds
    @ScreenModelKey(MainScreenVM::class)
    fun bindMainScreenViewModel(impl: MainScreenVM): ScreenModel

    @IntoMap
    @Binds
    @ScreenModelKey(AddCardViewModel::class)
    fun bindLAddCardScreenViewModel(impl: AddCardViewModel): ScreenModel
}

