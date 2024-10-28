package uz.gita.usecase.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import uz.gita.usecase.card.AddCardUC
import uz.gita.usecase.card.DeleteCardUC
import uz.gita.usecase.card.GetCardsUC
import uz.gita.usecase.card.UpdateCardUC
import uz.gita.usecase.card.impl.AddCardUCImpl
import uz.gita.usecase.card.impl.DeleteCardUCImpl
import uz.gita.usecase.card.impl.GetCardsUCImpl
import uz.gita.usecase.card.impl.UpdateCardUCImpl


/*
    Created by Boburjon Murodov 27/10/24 at 20:52
*/

@Module
@InstallIn(ActivityRetainedComponent::class)
internal interface CardUseCaseModule {
    @Binds
    fun bindGetCardsUC(impl: GetCardsUCImpl): GetCardsUC

    @Binds
    fun bindAddCardUC(impl: AddCardUCImpl): AddCardUC

    @Binds
    fun bindDeleteCardUC(impl: DeleteCardUCImpl): DeleteCardUC

    @Binds
    fun bindUpdateCardUC(impl: UpdateCardUCImpl): UpdateCardUC
}