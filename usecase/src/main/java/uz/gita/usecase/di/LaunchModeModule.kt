package uz.gita.usecase.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import uz.gita.usecase.launchmode.CheckPasswordUC
import uz.gita.usecase.launchmode.GetLaunchModeUC
import uz.gita.usecase.launchmode.SetFirstPasswordUC
import uz.gita.usecase.launchmode.SetVerifyPasswordUC
import uz.gita.usecase.launchmode.SignedInStateTrueUC
import uz.gita.usecase.launchmode.impl.CheckPasswordUCImpl
import uz.gita.usecase.launchmode.impl.GetLaunchModeUCImpl
import uz.gita.usecase.launchmode.impl.SetFirstPasswordUCImpl
import uz.gita.usecase.launchmode.impl.SetVerifyPasswordImpl
import uz.gita.usecase.launchmode.impl.SignedInStateTrueUCImpl


/*
    Created by Boburjon Murodov 18/10/24 at 17:39
*/

@Module
@InstallIn(ActivityRetainedComponent::class)

internal interface LaunchModeModule {
    @Binds
    fun bindGetLaunchModeUC(impl: GetLaunchModeUCImpl): GetLaunchModeUC

    @Binds
    fun bindSetLaunchModeUC(impl: SignedInStateTrueUCImpl): SignedInStateTrueUC

    @Binds
    fun bindSetFirstPasswordUC(impl: SetFirstPasswordUCImpl): SetFirstPasswordUC

    @Binds
    fun bindSetVerifyPasswordUC(impl: SetVerifyPasswordImpl): SetVerifyPasswordUC


    @Binds
    fun bindCheckPasswordUC(impl: CheckPasswordUCImpl): CheckPasswordUC
}