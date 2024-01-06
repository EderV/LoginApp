package com.eder.rider.preferences

import android.content.Context
import com.eder.rider.entities.UserAuth

class UserAuthPreferences(
    context: Context
) : SharedPreferencesBase(context, "UserAuthPrefs") {

    fun saveUserId(userAuth: UserAuth) {
        // TODO Add array
        saveString("UserId", userAuth.id)
    }

    fun getUserId(): String {
        return getString("UserId")
    }

}