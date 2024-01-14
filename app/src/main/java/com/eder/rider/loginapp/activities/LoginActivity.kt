package com.eder.rider.loginapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.eder.rider.loginapp.R
import com.eder.rider.loginapp.databinding.ActivityLoginBinding
import com.eder.rider.loginapp.viewmodels.LoginViewModel
import com.eder.rider.loginapp.viewmodels.ViewModelFactory
import com.eder.rider.requests.apis.AuthService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.lifecycle.ViewModelProviders

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    @Inject
    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {

        }
    }


}