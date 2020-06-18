package wisata.travel.sumbar.domain.repository


import com.google.firebase.auth.FirebaseAuth
import wisata.travel.sumbar.domain.ResultWrapper
import wisata.travel.sumbar.utils.awaitTaskCompletable

class AuthRepositoryImpl(
    private val auth: FirebaseAuth
) : AuthRepository {
    override suspend fun createAccountWithEmailAndPassword(
        email: String,
        password: String
    ): ResultWrapper<Exception, Unit> {
        return ResultWrapper.build {
            awaitTaskCompletable(
                auth.createUserWithEmailAndPassword(email, password)
            )
        }
    }

    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): ResultWrapper<Exception, Unit> {
        return ResultWrapper.build {
            awaitTaskCompletable(
                auth.signInWithEmailAndPassword(email, password)
            )
        }
    }

}