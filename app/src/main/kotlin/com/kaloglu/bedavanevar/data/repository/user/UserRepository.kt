package com.kaloglu.bedavanevar.data.repository.user

import androidx.lifecycle.MediatorLiveData
import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.kaloglu.bedavanevar.data.repository.devicetoken.DeviceTokenListRepository
import com.kaloglu.bedavanevar.domain.TableNames
import com.kaloglu.bedavanevar.domain.filters.Filters
import com.kaloglu.bedavanevar.domain.livedata.CountLiveData
import com.kaloglu.bedavanevar.domain.livedata.QueryLiveData
import com.kaloglu.bedavanevar.domain.livedata.TicketCountLiveData
import com.kaloglu.bedavanevar.domain.model.*
import com.kaloglu.bedavanevar.domain.repository.base.BaseListRepository
import com.kaloglu.bedavanevar.injection.scopes.PerApplication
import com.kaloglu.bedavanevar.viewobjects.CalculatedResource
import javax.inject.Inject
import javax.inject.Named

@PerApplication
class UserRepository @Inject constructor(
        @Named(TableNames.USERS)
        override val collectionRef: CollectionReference,
        private val deviceTokenRepository: DeviceTokenListRepository
) : BaseListRepository() {

    override lateinit var documentRef: DocumentReference

    override fun getModelClass() = UserDetail::class.java

    fun getAttendanceInfo(raffleId: String): MediatorLiveData<CalculatedResource?> {
        val attendanceInfoLiveData = MediatorLiveData<CalculatedResource?>()

        fun pushMediator(it: CalculatedResource?) {
            attendanceInfoLiveData.value = it
        }

        attendanceInfoLiveData.addSource(getEnrollCount(raffleId), ::pushMediator)
        attendanceInfoLiveData.addSource(getTicketCount(), ::pushMediator)

        return attendanceInfoLiveData

    }

    fun getDeviceToken(filters: Filters): QueryLiveData<DeviceToken> = deviceTokenRepository.get(filters)

    fun removeDeviceToken(deviceToken: String) {
        deviceTokenRepository.remove(deviceToken)
    }

    fun createEnrollRecord(
            raffleId: String,
            onSuccess: (Timestamp) -> Unit,
            onError: (Exception) -> Unit
    ) {
        documentRef.getFirstAvailableTicket(onError) {
            useTicket(documentRef, raffleId, onSuccess, onError)
        }
    }

    private fun getTicketCount(): TicketCountLiveData = TicketCountLiveData(documentRef.getTicketsQuery())

    private fun getEnrollCount(raffleId: String) =
            CountLiveData(
                    documentRef
                            .collection(TableNames.ENROLLS)
                            .whereEqualTo("raffleId", raffleId)
            )

}
