package com.eder.rider.preferences.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import com.eder.rider.common.model.UserAuth
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserAuthPrefsDefault @Inject constructor(
    @ApplicationContext private val context: Context
) : SharedPreferencesBase(), UserAuthPrefs {

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    override val sharedPreferences: SharedPreferences
        get() = EncryptedSharedPreferences
            .create(
                context,
                PREFS_FILE_NAME,
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )

    override fun saveUserAuth(userAuth: UserAuth) {
        val map = HashMap<String, String>()
        map[KEY_USER_ID] = userAuth.userId
        map[KEY_ACCESS_TOKEN] = userAuth.accessToken
        map[KEY_REFRESH_TOKEN] = userAuth.refreshToken

        saveMapString(map)
    }

    override fun deleteUserAuth() {
        val map = HashMap<String, String>()
        map[KEY_USER_ID] = ""
        map[KEY_ACCESS_TOKEN] = ""
        map[KEY_REFRESH_TOKEN] = ""

        saveMapString(map)
    }

    override fun getUserAuth(): UserAuth {
        return UserAuth(getUserId(), getAccessToken(), getRefreshToken())
    }

    override fun getUserId(): String {
        return getString(KEY_USER_ID)
    }

    override fun getAccessToken(): String {
        return getString(KEY_ACCESS_TOKEN)
    }

    override fun getRefreshToken(): String {
        return getString(KEY_REFRESH_TOKEN)
    }

    companion object {
        private const val PREFS_FILE_NAME = "UserAuthPrefs"

        const val KEY_USER_ID = "user-id"
        const val KEY_ACCESS_TOKEN = "access-token"
        const val KEY_REFRESH_TOKEN = "refresh-token"
    }

}