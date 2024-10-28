package uz.gita.usecase.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import uz.gita.boboor.bankingappcompose.domain.usecase.SignInResendUC
import uz.gita.boboor.bankingappcompose.domain.usecase.SignInUC
import uz.gita.boboor.bankingappcompose.domain.usecase.SignInVerifyUC
import uz.gita.boboor.bankingappcompose.domain.usecase.SignUpResendUC
import uz.gita.boboor.bankingappcompose.domain.usecase.SignUpUC
import uz.gita.boboor.bankingappcompose.domain.usecase.SignUpVerifyUC
import uz.gita.usecase.auth.impl.SignInResendUCImpl
import uz.gita.usecase.auth.impl.SignInUCImpl
import uz.gita.usecase.auth.impl.SignInVerifyUCImpl
import uz.gita.usecase.auth.impl.SignUpResendUCImpl
import uz.gita.usecase.auth.impl.SignUpUCImpl
import uz.gita.usecase.auth.impl.SignUpVerifyUCImpl


@Module
@InstallIn(ActivityRetainedComponent::class)
internal interface AuthUseCaseModule {
    @Binds
    fun provideSingInUC(impl: SignInUCImpl): SignInUC

    @Binds
    fun provideSignInVerifyUC(impl: SignInVerifyUCImpl): SignInVerifyUC

    @Binds
    fun providesSignInResend(impl: SignInResendUCImpl): SignInResendUC

    @Binds
    fun provideSignUpUC(impl: SignUpUCImpl): SignUpUC

    @Binds
    fun provideSingUpVerifyUC(impl: SignUpVerifyUCImpl): SignUpVerifyUC

    @Binds
    fun providesSignupResend(impl: SignUpResendUCImpl): SignUpResendUC
}