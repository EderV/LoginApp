package com.eder.rider.loginapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eder.rider.requests.RetrofitDefault
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var retrofitDefault: RetrofitDefault

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


}