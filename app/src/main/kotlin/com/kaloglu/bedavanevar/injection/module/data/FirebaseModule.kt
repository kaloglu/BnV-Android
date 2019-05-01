package com.kaloglu.bedavanevar.injection.module.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.kaloglu.bedavanevar.domain.TableNames
import com.kaloglu.bedavanevar.injection.scopes.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
abstract class FirebaseModule {

    @Module
    companion object {

        @JvmStatic
        @PerApplication
        @Provides
        fun firestore(): FirebaseFirestore {
            FirebaseFirestore.setLoggingEnabled(true)
            val fireStoreDb = FirebaseFirestore.getInstance()
            fireStoreDb.firestoreSettings = FirebaseFirestoreSettings.Builder()
                    .setPersistenceEnabled(true)
                    .build()
            return fireStoreDb
        }

        @JvmStatic
        @PerApplication
        @Provides
        @Named(TableNames.RAFFLE_LIST)
        fun raffleCollection(firestore: FirebaseFirestore) =
                firestore.collection(TableNames.RAFFLE_LIST)

        @JvmStatic
        @PerApplication
        @Provides
        @Named(TableNames.ATTENDEE_LIST)
        fun attendeeCollection(firestore: FirebaseFirestore) =
                firestore.collection(TableNames.ATTENDEE_LIST)

    }

}
