package com.kaloglu.bedavanevar.domain.model

import com.google.gson.annotations.SerializedName
import com.kaloglu.bedavanevar.domain.model.base.BaseModel

data class DeviceToken constructor(
        @SerializedName("deviceToken") val deviceToken: String? = null
) : BaseModel()
