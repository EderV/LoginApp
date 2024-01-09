package com.eder.rider.preferences.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

open class SharedPreferencesBase(
    context: Context,
    prefsName: String
) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)

    fun saveString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun saveMapString(map: Map<String, String>) {
        val sharedPrefsEditor = sharedPreferences.edit()
        map.forEach {
            sharedPrefsEditor.putString(it.key, it.value)
        }
        sharedPrefsEditor.apply()
    }

    fun getString(key: String, defaultValue: String = ""): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

}