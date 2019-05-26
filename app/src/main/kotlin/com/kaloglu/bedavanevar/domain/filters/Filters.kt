package com.kaloglu.bedavanevar.domain.filters

import com.google.firebase.firestore.Query

/**
 * Object for passing filters around.
 */
class Filters @JvmOverloads constructor(
        var equalToMap: MutableMap<String, Any> = mutableMapOf(),
        var sortMap: MutableMap<String, Query.Direction> = mutableMapOf()
) {
    /**
     * Add equalTo Parameters
     *
     * @param field field name
     * @param value value
     * **/
    fun addEqualTo(field: String, value: Any): Filters {
        equalToMap[field] = value
        return this
    }

    /**
     * Add orderBy Parameters
     *
     * @param field field name
     * @param direction ASCENDING or DESCENDING
     * **/
    @JvmOverloads
    fun addSort(field: String, direction: Query.Direction = Query.Direction.ASCENDING): Filters {
        sortMap[field] = direction
        return this
    }
}
