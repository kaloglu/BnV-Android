@file:JvmName("FirestoreUtil")

package com.kaloglu.bedavanevar.utils.extensions

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*
import com.kaloglu.bedavanevar.domain.TableNames
import com.kaloglu.bedavanevar.domain.model.Ticket
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
        onComplete: (Exception?) -> Unit = { _: Exception? -> }
) = getSubCollection(documentId, subCollectionName).addToCollection(model, onComplete)

@JvmOverloads
fun <M : BaseModel> DocumentReference.addToCollection(
        collectionName: String,
        model: M,
        onComplete: (Exception?) -> Unit = { _: Exception? -> }
) = collection(collectionName).addToCollection(model, onComplete)

@JvmOverloads
fun <M : BaseModel> CollectionReference.addToCollection(model: M, onComplete: (Exception?) -> Unit = { _: Exception? -> }) {
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

    return newDocument.set(model)(onComplete)
}

fun CollectionReference.getSubCollection(documentId: String, subCollectionName: String) =
        document(documentId).collection(subCollectionName)

fun <T : BaseModel> Class<T>.documentTo(mutableList: MutableList<DocumentSnapshot>) =
        mutableList.map(this::documentToObj)

fun <T : BaseModel> Class<T>.queryTo(querySnapshot: QuerySnapshot?) =
        querySnapshot?.map(this::queryToObj) ?: emptyList()

fun <T : BaseModel> Class<T>.documentToObj(documentSnapshot: DocumentSnapshot) =
        documentSnapshot.toObject(this)?.run {
            id = documentSnapshot.id
            this
        }!!

fun <T : BaseModel> Class<T>.queryToObj(querySnapshot: QueryDocumentSnapshot?) =
        querySnapshot?.toObject(this)!!.run {
            id = querySnapshot.id
            this
        }

fun <T : BaseModel> Query.getOnce(clazz: Class<T>, onSuccessListener: (List<T>) -> Unit) {
    this.get().addOnSuccessListener {
        onSuccessListener(clazz.queryTo(it))
    }
}