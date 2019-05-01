package com.kaloglu.bedavanevar.domain.model

import com.google.gson.annotations.SerializedName
import com.kaloglu.bedavanevar.domain.model.base.BaseModel

data class Media(
        @SerializedName("path") var path: String,
        @SerializedName("type") var type: Type
) : BaseModel() {

    enum class Type {
        IMAGE, VIDEO
    }
}