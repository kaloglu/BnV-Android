package com.kaloglu.bedavanevar.domain.enums

enum class UnitType(val value: String) {
    AD("Adet"),
    KG("Kg"),
    LT("Lt"),
    GR("Gr");

    companion object {

        fun convert(value: String): UnitType = when (value) {
            KG.value -> KG
            LT.value -> LT
            GR.value -> GR
            else -> AD
        }
    }
}