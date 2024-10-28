package uz.gita.usecase.launchmode.impl

import uz.gita.data.repository.AppRepository
import uz.gita.usecase.launchmode.SetFirstPasswordUC
import javax.inject.Inject


/*
    Created by Boburjon Murodov 26/10/24 at 17:35
*/

internal class SetFirstPasswordUCImpl @Inject constructor(private val repository: AppRepository) : SetFirstPasswordUC {
    override suspend fun invoke(password: String) {
        repository.setFirstPassword(password)
    }
}