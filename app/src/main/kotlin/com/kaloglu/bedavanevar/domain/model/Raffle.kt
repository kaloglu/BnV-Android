package com.kaloglu.bedavanevar.domain.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName
import com.kaloglu.bedavanevar.domain.model.base.BaseModel
import com.kaloglu.bedavanevar.utils.extensions.empty

@IgnoreExtraProperties
data class Raffle @JvmOverloads constructor(
        override var id: String = String.empty,
        @SerializedName("title") var title: String? = String.empty,
        @SerializedName("description") var description: String? = String.empty,
        @SerializedName("startDate") var startDate: Timestamp? = null,
        @SerializedName("endDate") var endDate: Timestamp? = null,
        @SerializedName("rules") var rules: RaffleRules? = null,
        @SerializedName("productInfo") var productInfo: ProductInfo? = null
) : BaseModel() {

    data class RaffleRules @JvmOverloads constructor(
            @SerializedName("maxAttendee") var maxAttendee: Int? = 1,
            @SerializedName("maxAttendByUser") var maxAttendByUser: Int? = 1
    )

    data class ProductInfo @JvmOverloads constructor(
            @SerializedName("name") var name: String? = String.empty,
            @SerializedName("images") var images: List<Media>? = emptyList(),
            @SerializedName("count") var count: String? = "1",
            @SerializedName("unit") var unit: String? = String.empty,
            @SerializedName("unitPrice") var unitPrice: Double? = 0.0
    )

//    val unitPrice: Money
//        get() = Money.of(unitPriceDouble)
//
//    val unit: UnitType
//        get() = UnitType.convert(unitType)

}
