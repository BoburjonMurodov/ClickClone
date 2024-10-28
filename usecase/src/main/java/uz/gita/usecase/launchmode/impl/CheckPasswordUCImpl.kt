package uz.gita.usecase.launchmode.impl

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.data.repository.AppRepository
import uz.gita.usecase.launchmode.CheckPasswordUC
import javax.inject.Inject


/*
    Created by Boburjon Murodov 26/10/24 at 19:59
*/


internal class CheckPasswordUCImpl @Inject constructor(private val repository: AppRepository) : CheckPasswordUC {
    override fun invoke(password: String): Flow<Boolean> = flow {
        val pass = repository.getPassword()
        Log.d("TTT", "real pass: $pass ")
        Log.d("TTT", "entered pass: $password ")

        emit(pass == password)
    }
}

