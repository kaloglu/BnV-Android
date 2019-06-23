package com.kaloglu.bedavanevar.domain.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.gson.annotations.SerializedName
import com.kaloglu.bedavanevar.domain.TableNames
import com.kaloglu.bedavanevar.domain.model.base.BaseModel
import com.kaloglu.bedavanevar.utils.extensions.*

data class Ticket @JvmOverloads constructor(
        override var id: String = String.empty,
        @SerializedName("source") var source: String = String.empty,
        @SerializedName("earn") var earn: Int = 0,
        @SerializedName("remain") var remain: Int = 0,
        @SerializedName("createDate") var createDate: Timestamp = currentTime(),
        @SerializedName("expireDate") var expireDate: Timestamp? = null,
        @SerializedName("lastUpdate") var lastUpdate: Timestamp = currentTime(),
        @SerializedName("userId") var userId: String = String.empty
) : BaseModel()

// Domain Extensions
fun <T : BaseModel> Class<T>.documentToTicket(mutableList: MutableList<DocumentSnapshot>) =
        this.documentTo(mutableList)

fun queryToTicket(querySnapshot: QuerySnapshot) = Ticket::class.java.queryTo(querySnapshot)

fun List<Ticket>.availableTickets() =
        filter {
            (it.expireDate == null || currentTime() <= it.expireDate) &&
                    currentTime() >= it.createDate && it.remain > 0
        }.sortedBy {
            it.createDate
        }

fun DocumentReference.getFirstAvailableTicket(
        onError: (Exception) -> Unit,
        onTicket: Ticket.() -> Unit
) = getTicketsQuery().limit(1)
        .getOnce(Ticket::class.java) {
            val availableTickets = it.availableTickets()
            if (availableTickets.isNotNullOrEmpty()) availableTickets.first().onTicket()
            else onError(Exception("Ticket Hatası: Ticket Bulunamadı!"))

        }

fun DocumentReference.getTicketsQuery() = getTicketCollection()
        .whereGreaterThanOrEqualTo("remain", 1)
        .orderBy("remain")

fun Ticket.update(
        documentReference: DocumentReference,
        onError: (Exception) -> Unit,
        onSuccess: () -> Unit
) {
    documentReference.getTicketCollection()
            .document(id)
            .set(this)
            .addOnFailureListener(onError)
            .addOnSuccessListener {
                onSuccess()
            }
}

private fun DocumentReference.getTicketCollection() = collection(TableNames.TICKETS)

fun Ticket.useTicket(
        documentRef: DocumentReference,
        raffleId: String,
        onSuccess: (Timestamp) -> Unit,
        onError: (Exception) -> Unit
) {
    remain--
    lastUpdate = Timestamp.now()

    update(documentRef, onError) {
        addEnrollDocument(documentRef, raffleId, onSuccess, onError)
    }
}

fun Ticket.addEnrollDocument(
        documentRef: DocumentReference,
        raffleId: String,
        onSuccess: Timestamp.() -> Unit,
        onError: (Exception) -> Unit
) {
    val enroll = Enroll(
            raffleId = raffleId,
            ticketId = id,
            enrollDate = lastUpdate
    )
    documentRef
            .addToCollection(TableNames.ENROLLS, enroll) { enrollError: Exception? ->
                when (enrollError) {
                    null -> lastUpdate.onSuccess()
                    else -> onError(enrollError)
                }
            }

}