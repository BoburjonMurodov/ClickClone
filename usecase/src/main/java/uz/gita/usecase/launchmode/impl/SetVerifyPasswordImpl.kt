package uz.gita.usecase.launchmode.impl

import uz.gita.data.repository.AppRepository
import uz.gita.usecase.launchmode.SetVerifyPasswordUC
import javax.inject.Inject


/*
    Created by Boburjon Murodov 26/10/24 at 17:34
*/

internal class SetVerifyPasswordImpl @Inject constructor(private val repository: AppRepository) : SetVerifyPasswordUC {
    override suspend fun invoke(password: String) {
        repository.setVerifyPassword(password)
    }
}