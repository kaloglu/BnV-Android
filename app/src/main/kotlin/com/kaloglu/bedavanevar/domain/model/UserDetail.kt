package com.kaloglu.bedavanevar.domain.model

import com.google.gson.annotations.SerializedName
import com.kaloglu.bedavanevar.domain.model.base.BaseModel

data class UserDetail(
        @SerializedName("name") val fullname: String,
        @SerializedName("username") val username: String,
        @SerializedName("email") val email: String,
        @SerializedName("providers") val providers: List<Provider>,
        @SerializedName("address") val address: String,
        @SerializedName("gsm") val gsm: String,
        @SerializedName("city") val city: String,
        @SerializedName("country") val country: String,
        @SerializedName("age") val age: Int,
        @SerializedName("gender") val gender: Gender
) : BaseModel() {

    data class Provider(
            @SerializedName("type") val type: String,
            @SerializedName("token") val token: String,
            @SerializedName("secret") val secret: String
    ) : BaseModel()

    enum class Gender(val value: String) {
        MALE("Erkek"),
        FEMALE("Kadın"),
        TRANS("Trans");
    }

}