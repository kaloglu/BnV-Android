package com.kaloglu.bedavanevar.domain.livedata

import androidx.lifecycle.LiveData
import com.google.firebase.firestore.*
import com.kaloglu.bedavanevar.domain.model.base.BaseModel
import com.kaloglu.bedavanevar.viewobjects.Resource

class QueryLiveData<T : BaseModel>(
        private val query: Query,
        private val typeClazz: Class<T>
) : LiveData<Resource<List<T>>>(), EventListener<QuerySnapshot> {

    private var registration: ListenerRegistration? = null

    override fun onEvent(snapshots: QuerySnapshot?, e: FirebaseFirestoreException?) {
        val listData = documentToList(snapshots)
        value = when {
            e != null -> Resource.error(e.localizedMessage, listData)
            listData.isNullOrEmpty() -> Resource.empty()
            else -> Resource.success(listData)
        }
    }

    override fun onActive() {
        super.onActive()
        value = Resource.loading()
        registration = query.addSnapshotListener(this)
    }

    override fun onInactive() {
        super.onInactive()
        if (registration != null) {
            registration!!.remove()
            registration = null
        }
    }

    private fun documentToList(snapshots: QuerySnapshot?): List<T> {
        return snapshots
                ?.takeIf { !it.isEmpty }
                ?.documents
                ?.mapTo(mutableListOf()) {
                    val toObject = it.toObject(typeClazz)!!
                    toObject.id = it.id
                    toObject
                }?.toList() ?: emptyList()
    }
}
