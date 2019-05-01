package com.kaloglu.bedavanevar.domain.model

import android.icu.math.BigDecimal
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName
import com.kaloglu.bedavanevar.domain.model.base.BaseModel

@IgnoreExtraProperties
data class Raffle @JvmOverloads constructor(
        @SerializedName("title") var title: String,
        @SerializedName("description") var description: String,
        @SerializedName("count") var count: Int,
        @SerializedName("unit") var unit: Unit,
        @SerializedName("unitPrice") var unitPrice: BigDecimal,
        @SerializedName("startDate") var startDate: Long,
        @SerializedName("endDate") var endDate: Long,
        @SerializedName("attendeeLimit") var attendeeLimit: Int,
        @SerializedName("images") var images: List<Media> = emptyList(),
        @SerializedName("attendees") var attendees: List<Attendee> = emptyList()
) : BaseModel() {

    enum class Unit(val value: String) {
        AD("Adet"),
        KG("Kg"),
        LT("Lt"),
        GR("Gr");

    }

}
