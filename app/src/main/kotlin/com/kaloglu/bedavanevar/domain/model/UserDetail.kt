package com.kaloglu.bedavanevar.domain.model

import com.google.gson.annotations.SerializedName
import com.kaloglu.bedavanevar.domain.model.base.BaseModel
import com.kaloglu.bedavanevar.utils.extensions.empty

data class UserDetail @JvmOverloads constructor(
        override var id: String = String.empty,
        @SerializedName("name") val fullname: String? = null,
        @SerializedName("username") val username: String? = null,
        @SerializedName("email") val email: String? = null,
        @SerializedName("providers") val providers: List<Provider>? = emptyList(),
        @SerializedName("address") val address: String? = null,
        @SerializedName("gsm") val gsm: String? = null,
        @SerializedName("city") val city: String? = null,
        @SerializedName("country") val country: String? = null,
        @SerializedName("age") val age: Int? = null,
        @SerializedName("gender") val gender: Gender? = null,
        @SerializedName("deviceToken") var deviceToken: String = String.empty,
        @SerializedName("profilePicUrl") var profilePicUrl: String? = null
) : BaseModel() {

    data class Provider(
            @SerializedName("type") val type: String,
            @SerializedName("deviceToken") val token: String,
            @SerializedName("secret") val secret: String
    ) : BaseModel()

    enum class Gender(val value: String) {
        MALE("Erkek"),
        FEMALE("Kadın"),
        TRANS("Trans");
    }

}