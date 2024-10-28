package uz.gita.usecase.launchmode.impl

import uz.gita.data.repository.AppRepository
import uz.gita.usecase.launchmode.SignedInStateTrueUC
import javax.inject.Inject


/*
    Created by Boburjon Murodov 26/10/24 at 17:13
*/

internal class SignedInStateTrueUCImpl @Inject constructor(private val repository: AppRepository) : SignedInStateTrueUC {
    override suspend fun invoke() {
        repository.setSignedInTrue()
    }
}