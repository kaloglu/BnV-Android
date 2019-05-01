package com.kaloglu.bedavanevar.data

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import javax.inject.Inject

class LocalStorage @Inject constructor(context: Context) {

    private val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    inline operator fun <reified T> SharedPreferences.get(key: String, defaultValue: T): T {
        return when {
            T::class == Boolean::class -> this.getBoolean(key, defaultValue as Boolean) as T
            T::class == Float::class -> this.getFloat(key, defaultValue as Float) as T
            T::class == Int::class -> this.getInt(key, defaultValue as Int) as T
            T::class == Long::class -> this.getLong(key, defaultValue as Long) as T
            T::class == String::class -> this.getString(key, defaultValue as String) as T
            else -> throw UnsupportedOperationException("Not yet implemented")
        }

    }

    inline operator fun <reified T> SharedPreferences.set(key: String, value: T) {
        edit().apply {
            when (T::class) {
                Boolean::class -> putBoolean(key, value as Boolean)
                Float::class -> putFloat(key, value as Float)
                Int::class -> putInt(key, value as Int)
                Long::class -> putLong(key, value as Long)
                String::class -> putString(key, value as String)
                else -> throw UnsupportedOperationException("Not yet implemented")
            }

            apply()
        }
    }

    companion object {
        const val PREF_KEY_TOKEN = "PREF_KEY_TOKEN"
        const val PREF_KEY_LONGITUDE = "PREF_KEY_LONGITUDE"
        const val PREF_KEY_LATITUDE = "PREF_KEY_LATITUDE"
//        const val SAMPLE_PARAMETER_KEY = "email"
    }

    fun getToken() = preferences[PREF_KEY_TOKEN, ""]

    fun setToken(value: String?) =
            when {
                value.isNullOrEmpty() -> preferences[PREF_KEY_TOKEN] = ""
                else -> preferences[PREF_KEY_TOKEN] = value
            }

    fun clearToken() = setToken(null)

}
