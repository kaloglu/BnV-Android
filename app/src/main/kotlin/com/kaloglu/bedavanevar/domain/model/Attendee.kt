package com.kaloglu.bedavanevar.domain.model

import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName
import com.kaloglu.bedavanevar.domain.model.base.BaseModel


@IgnoreExtraProperties
data class Attendee(
        @SerializedName("user") val user: UserDetail,
        @SerializedName("attendDate") val attendDate: Long
) : BaseModel()