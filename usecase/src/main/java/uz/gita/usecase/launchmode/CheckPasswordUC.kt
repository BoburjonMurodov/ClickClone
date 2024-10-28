package uz.gita.usecase.launchmode

import kotlinx.coroutines.flow.Flow


/*
    Created by Boburjon Murodov 26/10/24 at 19:52
*/


interface CheckPasswordUC {
    operator fun invoke(password: String): Flow<Boolean>
}