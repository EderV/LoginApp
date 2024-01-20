package com.eder.rider.loginapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eder.rider.loginapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    private var waitJob: Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        waitJob = CoroutineScope(Dispatchers.IO).launch {
            delay(3000)

            val intent = Intent(this@SplashScreen, LoginActivity::class.java)
            super.startActivity(intent)
            super.finish()
        }
    }

    override fun onDestroy() {
        waitJob?.cancel()
        super.onDestroy()
    }

}