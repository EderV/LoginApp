package com.eder.rider.preferences.sharedpreferences

import android.content.SharedPreferences

abstract class SharedPreferencesBase {

    abstract val sharedPreferences: SharedPreferences

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