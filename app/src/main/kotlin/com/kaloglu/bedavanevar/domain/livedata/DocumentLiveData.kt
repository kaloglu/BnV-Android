package com.kaloglu.bedavanevar.domain.livedata

import androidx.lifecycle.LiveData
import com.google.firebase.firestore.*
import com.kaloglu.bedavanevar.domain.model.base.BaseModel
import com.kaloglu.bedavanevar.utils.extensions.documentToObj
import com.kaloglu.bedavanevar.viewobjects.Resource

class DocumentLiveData<T : BaseModel>(
        private val document: DocumentReference,
        private val type: Class<T>
) : LiveData<Resource<T>>(), EventListener<DocumentSnapshot> {
    private var registration: ListenerRegistration? = null

    override fun onEvent(snapshot: DocumentSnapshot?, e: FirebaseFirestoreException?) {

        val data = documentToModel(snapshot)
        value = when {
            e != null -> Resource.Error(e.localizedMessage)
            data == null -> Resource.Empty()
            else -> Resource.Success(data)
        }
    }

    override fun onActive() {
        super.onActive()
        value = Resource.Loading()
        registration = document.addSnapshotListener(this)
    }

    override fun onInactive() {
        super.onInactive()
        if (registration != null) {
            registration!!.remove()
            registration = null
        }
    }

    private fun documentToModel(snapshot: DocumentSnapshot?) = snapshot?.let(type::documentToObj)

}
