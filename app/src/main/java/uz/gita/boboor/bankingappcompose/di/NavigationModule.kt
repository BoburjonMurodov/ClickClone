package uz.gita.boboor.bankingappcompose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.boboor.bankingappcompose.ui.navigation.AppNavigationDispatcher
import uz.gita.boboor.bankingappcompose.ui.navigation.AppNavigator
import uz.gita.boboor.bankingappcompose.ui.navigation.NavigationHandler


@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {
    @Binds
    fun bindAppNavigation(dispatcher: AppNavigationDispatcher): AppNavigator

    @Binds
    fun bindAppNavigationHandler(dispatcher: AppNavigationDispatcher): NavigationHandler
}