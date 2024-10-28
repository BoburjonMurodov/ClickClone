package uz.gita.boboor.bankingappcompose.domain.usecase

import kotlinx.coroutines.flow.Flow

interface SignUpResendUC {
    suspend operator fun invoke(): Flow<Result<Unit>>
}