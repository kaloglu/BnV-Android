package com.kaloglu.bedavanevar.domain.model

import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName
import com.kaloglu.bedavanevar.domain.enums.UnitType
import com.kaloglu.bedavanevar.domain.model.base.BaseModel
import com.kaloglu.bedavanevar.utils.extensions.empty

@IgnoreExtraProperties
data class Raffle @JvmOverloads constructor(
        @SerializedName("title") var title: String = String.empty,
        @SerializedName("description") var description: String = String.empty,
        @SerializedName("count") var count: Int = 1,
        @SerializedName("unit") var unitType: String = String.empty,
        @SerializedName("unitPrice") var unitPriceDouble: Double = 0.0,
        @SerializedName("startDate") var startDate: Long? = null,
        @SerializedName("endDate") var endDate: Long? = null,
        @SerializedName("maxAttendee") var maxAttendee: Int = Short.MAX_VALUE.toInt(),
        @SerializedName("maxAttendForUser") var maxAttendForUser: Int = Short.MAX_VALUE.toInt(),
        @SerializedName("images") var images: List<Media>? = emptyList(),
        @SerializedName("attendees") var attendees: List<Attendee>? = emptyList()
) : BaseModel() {

    val unitPrice: Money
        get() = Money.of(unitPriceDouble)

    val unit: UnitType
        get() = UnitType.convert(unitType)

}
