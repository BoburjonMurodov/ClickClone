package uz.gita.usecase.launchmode


/*
    Created by Boburjon Murodov 26/10/24 at 17:34
*/

interface SetVerifyPasswordUC {
    suspend operator fun invoke(password: String)
}