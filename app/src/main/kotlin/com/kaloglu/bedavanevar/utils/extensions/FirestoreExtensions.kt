@file:JvmName("FirestoreUtil")

package com.kaloglu.bedavanevar.utils.extensions

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.kaloglu.bedavanevar.domain.model.Attendee
import com.kaloglu.bedavanevar.domain.model.base.BaseModel

operator fun <TResult, M> Task<TResult>.invoke(model: M, onComplete: (M, Exception?) -> Unit) {
    addOnCompleteListener {
        onComplete(model, it.exception)
    }
}

operator fun <TResult> Task<TResult>.invoke(onComplete: (Exception?) -> Unit) {
    addOnCompleteListener {
        onComplete(it.exception)
    }
}

@JvmOverloads
fun <M : BaseModel> CollectionReference.addToCollection(
        documentId: String,
        subCollectionName: String,
        model: M,
        onComplete: (M, Exception?) -> Unit = { _: M, _: Exception? -> }
) = getSubCollection(documentId, subCollectionName).addToCollection(model, onComplete)

@JvmOverloads
fun <M : BaseModel> DocumentReference.addToCollection(
        collectionName: String,
        model: M,
        onComplete: (M, Exception?) -> Unit = { _: M, _: Exception? -> }
) = collection(collectionName).addToCollection(model, onComplete)

@JvmOverloads
fun <M : BaseModel> CollectionReference.addToCollection(model: M, onComplete: (M, Exception?) -> Unit = { _: M, _: Exception? -> }) {
    val newDocument: DocumentReference
    when {
        model.id.isNotEmpty() -> {
            newDocument = document(model.id)
        }
        else -> {
            newDocument = document()
            model.id = newDocument.id
        }
    }

    return newDocument.set(model)(model, onComplete)
}

fun CollectionReference.getSubCollection(documentId: String, subCollectionName: String) =
        document(documentId).collection(subCollectionName)
