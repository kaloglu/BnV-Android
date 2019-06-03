package com.kaloglu.bedavanevar.domain.model

import com.google.gson.annotations.SerializedName
import com.kaloglu.bedavanevar.utils.extensions.empty

data class Media @JvmOverloads constructor(
        @SerializedName("path") var path: String? = String.empty,
        @SerializedName("type") var type: String? = String.empty
) {

    enum class Type {
        IMAGE, VIDEO
    }
}