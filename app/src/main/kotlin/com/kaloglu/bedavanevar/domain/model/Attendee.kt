package com.kaloglu.bedavanevar.domain.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName
import com.kaloglu.bedavanevar.domain.model.base.BaseModel
import com.kaloglu.bedavanevar.utils.extensions.empty


@IgnoreExtraProperties
data class Attendee @JvmOverloads constructor(
        @SerializedName("userId") val userId: String? = String.empty,
        @SerializedName("attendDate") var attendDate: Timestamp? = null
) : BaseModel()