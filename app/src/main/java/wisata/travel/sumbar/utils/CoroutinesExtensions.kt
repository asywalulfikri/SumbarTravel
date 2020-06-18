package wisata.travel.sumbar.utils

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

//Wraps Firebase/GMS calls
internal suspend fun <T> awaitTaskResult(task: Task<T>): T = suspendCoroutine { continuation ->
    task.addOnCompleteListener { task ->
        if (task.isSuccessful) {
            continuation.resume(task.result!!)
        } else {
            continuation.resumeWithException(task.exception!!)
        }
    }
}

//Wraps Firebase/GMS calls
internal suspend fun <T> awaitTaskCompletable(task: Task<T>): Unit =
    suspendCoroutine { continuation ->
        task.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                continuation.resume(Unit)
            } else {
                continuation.resumeWithException(task.exception!!)
            }
        }
    }

@ExperimentalCoroutinesApi
internal fun Query.getQuerySnapshotFlow(): Flow<QuerySnapshot?> {
    return callbackFlow {
        val listenerRegistration =
            addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                if (firebaseFirestoreException != null) {
                    cancel(
                        message = "error fetching collection data",
                        cause = firebaseFirestoreException
                    )
                    return@addSnapshotListener
                }
                offer(querySnapshot)
            }
        awaitClose {
            Timber.d("cancelling the listener on collection")
            listenerRegistration.remove()
        }
    }
}

@ExperimentalCoroutinesApi
internal fun <T> Query.getDataFlow(mapper: (QuerySnapshot?) -> T): Flow<T> {
    return getQuerySnapshotFlow()
        .map {
            return@map mapper(it)
        }
}

@ExperimentalCoroutinesApi
internal fun DocumentReference.getDocumentSnapshotFlow(): Flow<DocumentSnapshot?> {
    return callbackFlow {
        val listenerRegistration =
            addSnapshotListener { documentSnapshot, firebaseFirestoreException ->
                if (firebaseFirestoreException != null) {
                    cancel(
                        message = "error fetching document data",
                        cause = firebaseFirestoreException
                    )
                    return@addSnapshotListener
                }
                offer(documentSnapshot)
            }
        awaitClose {
            Timber.d("cancelling the listener on collection")
            listenerRegistration.remove()
        }
    }
}

@ExperimentalCoroutinesApi
internal fun <T> DocumentReference.getDataFlow(mapper: (DocumentSnapshot?) -> T): Flow<T> {
    return getDocumentSnapshotFlow()
        .map {
            return@map mapper(it)
        }
}