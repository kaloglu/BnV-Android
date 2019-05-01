package com.kaloglu.bedavanevar.domain.model.base

import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName
import com.kaloglu.bedavanevar.utils.extensions.empty
import java.io.Serializable

@IgnoreExtraProperties
abstract class BaseModel @JvmOverloads constructor(
        @SerializedName("id") var id: String = String.empty
) : Serializable {
    companion object {
        const val serialVersionUID = 1L
    }

}

