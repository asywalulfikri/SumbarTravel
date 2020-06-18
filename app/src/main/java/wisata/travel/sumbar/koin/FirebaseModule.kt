package wisata.travel.sumbar.koin

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import org.koin.dsl.module

/**
 * Module that holds
 */
val firebaseModule = module {
    factory { FirebaseAuth.getInstance() }
    factory { FirebaseFirestore.getInstance() }
    factory { FirebaseStorage.getInstance() }
}