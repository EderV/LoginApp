package com.eder.rider.preferences.sharedpreferences

import android.content.Context
import com.eder.rider.entities.UserAuth
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class UserAuthPrefsDefault @Inject constructor(
    @ApplicationContext context: Context
) : SharedPreferencesBase(context, "UserAuthPrefs"), UserAuthPrefs {

    override fun saveUserAuth(userAuth: UserAuth) {
        val map = HashMap<String, String>()
        map[KEY_USER_ID] = userAuth.id
        map[KEY_AUTH_TOKEN] = userAuth.authToken
        map[KEY_REFRESH_TOKEN] = userAuth.refreshToken

        saveMapString(map)
    }

    override fun getUserAuth(): UserAuth {
        return UserAuth(getUserId(), getAuthToken(), getRefreshToken())
    }

    override fun getUserId(): String {
        return getString(KEY_USER_ID)
    }

    override fun getAuthToken(): String {
        return getString(KEY_AUTH_TOKEN)
    }

    override fun getRefreshToken(): String {
        return getString(KEY_AUTH_TOKEN)
    }

    companion object {
        const val KEY_USER_ID = "user-id"
        const val KEY_AUTH_TOKEN = "auth-token"
        const val KEY_REFRESH_TOKEN = "refresh-token"
    }

}