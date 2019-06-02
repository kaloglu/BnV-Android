package com.kaloglu.bedavanevar.domain.model

import com.google.gson.annotations.SerializedName
import com.kaloglu.bedavanevar.domain.model.base.BaseModel
import com.kaloglu.bedavanevar.utils.extensions.empty

data class UserDetail @JvmOverloads constructor(
        override var id: String = String.empty,
        @SerializedName("name") val fullname: String? = null,
        @SerializedName("username") val username: String? = null,
        @SerializedName("email") val email: String? = null,
        @SerializedName("providers") val providers: MutableList<Provider> = mutableListOf(),
        @SerializedName("address") val address: String? = null,
        @SerializedName("gsm") var gsm: String? = null,
        @SerializedName("city") val city: String? = null,
        @SerializedName("country") val country: String? = null,
        @SerializedName("age") val age: Int? = null,
        @SerializedName("gender") val gender: Gender? = null,
        @SerializedName("deviceToken") var deviceToken: String = String.empty,
        @SerializedName("profilePicUrl") var profilePicUrl: String? = null
) : BaseModel() {

    data class Provider @JvmOverloads constructor(
            override var id: String = String.empty,
            @SerializedName("deviceToken") var token: String = String.empty
    ) : BaseModel()

    enum class Gender(val value: String) {
        MALE("Erkek"),
        FEMALE("Kadın"),
        TRANS("Trans");
    }


}
