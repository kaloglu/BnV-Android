package com.kaloglu.bedavanevar.domain.livedata

import androidx.lifecycle.LiveData
import com.google.firebase.firestore.*

class CountLiveData(
        private val query: Query
) : LiveData<String>(), EventListener<QuerySnapshot> {
    private var registration: ListenerRegistration? = null

    override fun onEvent(snapshot: QuerySnapshot?, e: FirebaseFirestoreException?) {
        value = when {
            e != null -> null
            else -> snapshot?.size().toString()
        }
    }

    override fun onActive() {
        super.onActive()
        value = "0"
        registration = query.addSnapshotListener(this)
    }

    override fun onInactive() {
        super.onInactive()
        if (registration != null) {
            registration!!.remove()
            registration = null
        }
    }

}
