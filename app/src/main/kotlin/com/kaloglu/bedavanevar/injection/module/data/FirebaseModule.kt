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
        @Named(TableNames.USER_LIST)
        fun userCollection(firestore: FirebaseFirestore) =
                firestore.collection(TableNames.USER_LIST)

        @JvmStatic
        @PerApplication
        @Provides
        @Named(TableNames.ADMIN_LIST)
        fun adminCollection(firestore: FirebaseFirestore) =
                firestore.collection(TableNames.ADMIN_LIST)

        @JvmStatic
        @PerApplication
        @Provides
        @Named(TableNames.DEVICE_TOKENS)
        fun deviceTokenDocument(firestore: FirebaseFirestore) =
                firestore.collection(TableNames.DEVICE_TOKENS).document(TableNames.USER_LIST)

        @JvmStatic
        @PerApplication
        @Provides
        @Named(TableNames.UNREGISTERED_TOKENS)
        fun unRegisteredDeviceTokenCollection(firestore: FirebaseFirestore) =
                deviceTokenDocument(firestore)
                        .collection(TableNames.UNREGISTERED_TOKENS)

        @JvmStatic
        @PerApplication
        @Provides
        @Named(TableNames.RAFFLE_LIST)
        fun raffleCollection(firestore: FirebaseFirestore) =
                firestore.collection(TableNames.RAFFLE_LIST)

    }

}
