package com.kaloglu.bedavanevar.domain.model.base

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.kaloglu.bedavanevar.utils.extensions.empty
import java.io.Serializable

@IgnoreExtraProperties
abstract class BaseModel : Serializable {
    @get:Exclude
    open var id: String = String.empty


    companion object {
        const val serialVersionUID = 1L
    }

}

