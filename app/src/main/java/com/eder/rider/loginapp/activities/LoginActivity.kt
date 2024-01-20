package com.eder.rider.loginapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.eder.rider.loginapp.databinding.ActivityLoginBinding
import com.eder.rider.loginapp.di.LoginViewModelFactory
import com.eder.rider.loginapp.viewmodels.LoginViewModel
import com.eder.rider.requests.model.UserLogin
import es.evm.exmpl.common.Progress
import dagger.hilt.android.AndroidEntryPoint
import es.evm.exmpl.common.toaster.Toaster
import es.evm.exmpl.ui.R
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    @Inject
    lateinit var toaster: Toaster

    @Inject
    lateinit var viewModelFactory: LoginViewModelFactory

    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Login"

        binding.btnGoRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            super.startActivity(intent)
            super.finish()
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            val userLogin = UserLogin(username, password)
            viewModel.login(
                userLogin,
                {
                    toaster.showShortToast(super.getString(R.string.login_success))

                    val intent = Intent(this, MainActivity::class.java)
                    super.startActivity(intent)
                    super.finish()
                },
                {
                    toaster.showShortToast(super.getString(R.string.login_failed))
                    viewModel.overlayProgressMld.value = Progress.hide()
                }
            )
        }

        viewModel.overlayProgressMld.observe(this) {
            binding.progress = it
            Log.e("AAAAAA", "Progres status: ${it.status}")
        }
    }

    companion object {
        private const val TAG = "LoginActivity"
    }

}