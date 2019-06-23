package com.kaloglu.bedavanevar.viewobjects

import com.kaloglu.bedavanevar.domain.enums.Status
import com.kaloglu.bedavanevar.domain.enums.Status.*

sealed class Resource<out M> constructor(
        open val status: Status
) {
    open val data: M? = null
    open val message: String? = null

    data class Success<M>(override val data: M) : Resource<M>(SUCCESS)

    data class Error(override val message: String) : Resource<Nothing>(ERROR)

    class Loading : Resource<Nothing>(LOADING)

    class Empty : Resource<Nothing>(EMPTY)

}

sealed class CalculatedResource {
    open val result: Number? = null

    data class EnrollCount(override val result: Number) : CalculatedResource()
    data class TicketCount(override val result: Number) : CalculatedResource()
}