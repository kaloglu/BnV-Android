package com.kaloglu.library

import java.io.Serializable

abstract class BaseModel : Serializable {
    open var id: String = String.empty

    companion object {
        const val serialVersionUID = 1L
    }

}

