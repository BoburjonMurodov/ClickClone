package uz.gita.boboor.bankingappcompose.domain.usecase

import kotlinx.coroutines.flow.Flow

interface SignInResendUC {
    operator fun invoke(): Flow<Result<Unit>>
}