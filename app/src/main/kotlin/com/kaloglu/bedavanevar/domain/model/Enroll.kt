package com.kaloglu.bedavanevar.domain.model

import com.google.firebase.Timestamp
import com.google.gson.annotations.SerializedName
import com.kaloglu.bedavanevar.domain.model.base.BaseModel
import com.kaloglu.bedavanevar.utils.extensions.currentTime
import com.kaloglu.bedavanevar.utils.extensions.empty

data class Enroll @JvmOverloads constructor(
        override var id: String = String.empty,
        @SerializedName("ticketId") var ticketId: String = String.empty,
        @SerializedName("raffleId") var raffleId: String = String.empty,
        @SerializedName("enrollDate") var enrollDate: Timestamp = currentTime()
) : BaseModel()