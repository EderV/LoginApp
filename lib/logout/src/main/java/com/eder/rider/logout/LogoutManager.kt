package com.eder.rider.logout

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.eder.rider.common.GlobalConstants
import com.eder.rider.common.toaster.Toaster
import com.eder.rider.preferences.sharedpreferences.UserAuthPrefs
import com.eder.rider.ui.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LogoutManager @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val authPrefs: UserAuthPrefs
) {

    fun logout(activity: AppCompatActivity) {
        authPrefs.deleteUserAuth()
        val intent = createLoginIntent()
        activity.startActivity(intent)
        activity.finish()
    }

    fun logoutWithDialog(activity: AppCompatActivity) {
        AlertDialog.Builder(activity)
            .setTitle(R.string.logout_dialog_title)
            .setPositiveButton(R.string.yes) { dialog, _ ->
                dialog.dismiss()
                this@LogoutManager.logout(activity)
            }
            .setNegativeButton(R.string.no) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .create()
            .apply {
                setOnShowListener {
                    getButton(AlertDialog.BUTTON_POSITIVE)
                        .setBackgroundColor(activity.resources.getColor(R.color.primary, activity.resources.newTheme()))
                    getButton(AlertDialog.BUTTON_NEGATIVE)
                        .setBackgroundColor(activity.resources.getColor(R.color.accent, activity.resources.newTheme()))
                }
            }
            .show()
    }

    fun forceLogout() {
        authPrefs.deleteUserAuth()

        val intent = createLoginIntent()
        intent.putExtra(GlobalConstants.SPLASHSCREEN_LOGOUT_TOAST_KEY, "Forced logout")
        appContext.startActivity(intent)
    }

    private fun createLoginIntent(): Intent {
        val intent = Intent("action.login-activity.open")
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        return intent
    }

}