package wisata.travel.sumbar.domain.repository

import wisata.travel.sumbar.domain.ResultWrapper


interface AuthRepository {
    suspend fun createAccountWithEmailAndPassword(
        email: String,
        password: String
    ): ResultWrapper<Exception, Unit>

    suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): ResultWrapper<Exception, Unit>
}