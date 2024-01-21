package com.eder.rider.loginapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.eder.rider.loginapp.databinding.ActivityLoginBinding
import com.eder.rider.loginapp.di.LoginViewModelFactory
import com.eder.rider.loginapp.viewmodels.LoginViewModel
import com.eder.rider.main_activity.MainActivity
import com.eder.rider.requests.model.UserLogin
import com.eder.rider.common.Progress
import dagger.hilt.android.AndroidEntryPoint
import com.eder.rider.common.toaster.Toaster
import com.eder.rider.ui.R
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

            if (this.checkForm(username, password)) {
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
                        toaster.showLongToast(super.getString(R.string.login_failed))

                        viewModel.overlayProgressMld.value = Progress.hide()
                    }
                )
            }
        }

        viewModel.overlayProgressMld.observe(this) {
            binding.progress = it
            Log.e("AAAAAA", "Progres status: ${it.status}")
        }
    }

    private fun checkForm(username: String, password: String): Boolean {
        val usernameOk = this.checkUsername(username)
        val passwordOk = this.checkPassword(password)

        return usernameOk && passwordOk
    }

    private fun checkUsername(username: String): Boolean {
        try {
            if (viewModel.verifyUsername(username)) {
                binding.usernameTextInputLayout.error = ""
                return true
            }
        } catch (ex: IllegalArgumentException) {
            binding.usernameTextInputLayout.error = resources.getString(R.string.field_with_illegal_characters)
        }

        return false
    }

    private fun checkPassword(password: String): Boolean {
        try {
            if (viewModel.verifyPassword(password)) {
                binding.passwordTextInputLayout.error = ""

                return true
            }
        } catch (ex: IllegalArgumentException) {
            binding.passwordTextInputLayout.error = resources.getString(R.string.field_with_illegal_characters)
        }

        return false
    }

    companion object {
        private const val TAG = "LoginActivity"
    }

}