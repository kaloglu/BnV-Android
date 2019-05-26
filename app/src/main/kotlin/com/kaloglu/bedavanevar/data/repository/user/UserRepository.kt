package com.kaloglu.bedavanevar.data.repository.user

import com.google.firebase.firestore.CollectionReference
import com.kaloglu.bedavanevar.data.repository.devicetoken.DeviceTokenRepository
import com.kaloglu.bedavanevar.domain.QueryLiveData
import com.kaloglu.bedavanevar.domain.TableNames
import com.kaloglu.bedavanevar.domain.filters.Filters
import com.kaloglu.bedavanevar.domain.model.DeviceToken
import com.kaloglu.bedavanevar.domain.model.UserDetail
import com.kaloglu.bedavanevar.domain.repository.base.BaseRepository
import com.kaloglu.bedavanevar.injection.scopes.PerApplication
import javax.inject.Inject
import javax.inject.Named

@PerApplication
class UserRepository @Inject constructor(
        @Named(TableNames.USER_LIST)
        override val collectionRef: CollectionReference,
        private val deviceTokenRepository: DeviceTokenRepository
) : BaseRepository() {

    override fun getModelClass() = UserDetail::class.java

    fun getDeviceToken(filters: Filters): QueryLiveData<DeviceToken> = deviceTokenRepository.get(filters)

    fun removeDeviceToken(deviceToken: String) {
        deviceTokenRepository.remove(deviceToken)
    }

}
