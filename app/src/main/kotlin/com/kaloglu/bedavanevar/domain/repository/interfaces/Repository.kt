package com.kaloglu.bedavanevar.domain.repository.interfaces

import androidx.annotation.CallSuper
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.kaloglu.bedavanevar.domain.livedata.DocumentLiveData
import com.kaloglu.bedavanevar.domain.model.base.BaseModel

interface Repository {
    val collectionRef: CollectionReference
    var documentRef: DocumentReference

    fun getModelClass(): Class<*>

    @Suppress("UNCHECKED_CAST")
    @CallSuper
    fun <M : BaseModel> get(id: String): DocumentLiveData<M> {
        documentRef = collectionRef.document(id)
        return DocumentLiveData(documentRef, getModelClass() as Class<M>)
    }

}
