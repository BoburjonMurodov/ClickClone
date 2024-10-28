package uz.gita.usecase.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import uz.gita.usecase.home.GetBasicInfoUC
import uz.gita.usecase.home.GetFullInfoUC
import uz.gita.usecase.home.GetTotalBalanceUC
import uz.gita.usecase.home.impl.GetBasicInfoUCImpl
import uz.gita.usecase.home.impl.GetFullInfoUCImpl
import uz.gita.usecase.home.impl.GetTotalBalanceUCIMpl


/* Created by Boburjon Murodov 27/10/24 at 18:44 */



@Module
@InstallIn(ActivityRetainedComponent::class)

internal interface HomeUseCaseModule {
    @Binds
    fun bindGetBasicInfoUC(impl: GetBasicInfoUCImpl): GetBasicInfoUC

    @Binds
    fun bindGetTotalBalanceUC(impl: GetTotalBalanceUCIMpl): GetTotalBalanceUC

    @Binds
    fun bindGetFullInfoUC(impl: GetFullInfoUCImpl): GetFullInfoUC
}