package uz.gita.usecase.launchmode


/*
    Created by Boburjon Murodov 26/10/24 at 17:33
*/

interface SetFirstPasswordUC {
    suspend operator fun invoke(password: String)
}