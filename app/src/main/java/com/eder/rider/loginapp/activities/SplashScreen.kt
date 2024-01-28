package com.eder.rider.loginapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eder.rider.common.GlobalConstants
import com.eder.rider.common.toaster.Toaster
import com.eder.rider.loginapp.R
import com.eder.rider.main_activity.MainActivity
import com.eder.rider.preferences.sharedpreferences.UserAuthPrefs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : AppCompatActivity() {

    @Inject
    lateinit var authPrefs: UserAuthPrefs

    @Inject
    lateinit var toaster: Toaster

    private var waitJob: Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        if (intent.extras?.containsKey(GlobalConstants.SPLASHSCREEN_LOGOUT_TOAST_KEY) == true) {
            toaster.showLongToast(resources.getString(com.eder.rider.ui.R.string.login_again))
        }

        waitJob = CoroutineScope(Dispatchers.IO).launch {
            delay(3000)

            if (authPrefs.getUserId().isEmpty()) {
                val intent = Intent(this@SplashScreen, LoginActivity::class.java)
                super.startActivity(intent)
                super.finish()
            }
            else {
                val intent = Intent(this@SplashScreen, MainActivity::class.java)
                super.startActivity(intent)
                super.finish()
            }

        }
    }

    override fun onDestroy() {
        waitJob?.cancel()
        super.onDestroy()
    }

}