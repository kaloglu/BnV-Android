package com.kaloglu.bedavanevar.domain.livedata

import androidx.lifecycle.LiveData
import com.google.firebase.firestore.*
import com.kaloglu.bedavanevar.domain.model.availableTickets
import com.kaloglu.bedavanevar.domain.model.queryToTicket
import com.kaloglu.bedavanevar.viewobjects.CalculatedResource

abstract class CalculateLiveData(
        private val query: Query,
        val calculation: (QuerySnapshot) -> CalculatedResource? = { _ -> null }
) : LiveData<CalculatedResource?>(), EventListener<QuerySnapshot> {
    private var registration: ListenerRegistration? = null

    override fun onEvent(snapshot: QuerySnapshot?, e: FirebaseFirestoreException?) {
        value = when {
            e != null || snapshot == null -> null
            else -> calculateTo(snapshot)
        }
    }

    override fun onActive() {
        super.onActive()
        value = null
        registration = query.addSnapshotListener(this)
    }

    override fun onInactive() {
        super.onInactive()
        if (registration != null) {
            registration!!.remove()
            registration = null
        }
    }

    private fun calculateTo(snapshots: QuerySnapshot) = calculation(snapshots)

}

class CountLiveData(query: Query) : CalculateLiveData(query, { CalculatedResource.EnrollCount(it.size()) })

class TicketCountLiveData(query: Query) : CalculateLiveData(
        query,
        {
            queryToTicket(it)
                    .availableTickets()
                    .sumBy { ticket ->
                        ticket.remain
                    }.run {
                        CalculatedResource.TicketCount(this)
                    }
        }
)
