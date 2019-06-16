package com.kaloglu.bedavanevar.data.repository.devicetoken

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.kaloglu.bedavanevar.domain.TableNames
import com.kaloglu.bedavanevar.domain.model.DeviceToken
import com.kaloglu.bedavanevar.domain.repository.base.BaseListRepository
import com.kaloglu.bedavanevar.injection.scopes.PerApplication
import javax.inject.Inject
import javax.inject.Named

@PerApplication
class DeviceTokenListRepository @Inject constructor(
        @Named(TableNames.UNREGISTERED_TOKENS)
        override val collectionRef: CollectionReference,
        @Named(TableNames.DEVICE_TOKENS)
        override var documentRef: DocumentReference
) : BaseListRepository() {

    override fun getModelClass() = DeviceToken::class.java

}
