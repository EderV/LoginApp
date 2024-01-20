package com.eder.rider.loginapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eder.rider.loginapp.R
import com.eder.rider.loginapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Register"
    }

}