package com.eder.rider.loginapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.eder.rider.ui.R
import com.eder.rider.loginapp.databinding.ActivityRegisterBinding
import com.eder.rider.loginapp.di.RegisterViewModelFactory
import com.eder.rider.loginapp.viewmodels.RegisterViewModel
import com.eder.rider.requests.model.UserRegister
import dagger.hilt.android.AndroidEntryPoint
import com.eder.rider.common.Progress
import com.eder.rider.common.toaster.Toaster
import javax.inject.Inject

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    @Inject
    lateinit var toaster: Toaster

    @Inject
    lateinit var viewModelFactory: RegisterViewModelFactory

    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[RegisterViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Register"

        binding.btnRegister.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            val repeatPassword = binding.etRepeatPassword.text.toString()

            if (this.checkForm(email, username, password, repeatPassword)) {
                val userRegister = UserRegister(email, username, password)
                viewModel.register(
                    userRegister,
                    {
                        this.toaster.showShortToast(resources.getString(R.string.register_success))

                        val intent = Intent(this, LoginActivity::class.java)
                        super.startActivity(intent)
                        super.finish()
                    }, {
                        this.toaster.showLongToast(resources.getString(R.string.register_failed))

                        viewModel.overlayProgressMld.value = Progress.hide()
                    }
                )
            }
        }
    }

    private fun checkForm(
        email: String,
        username: String,
        password: String,
        repeatPassword: String
    ): Boolean {
        val emailOk = this.checkEmail(email)
        val usernameOk = this.checkUsername(username)
        val passwordsOk = this.checkPasswords(password, repeatPassword)

        return emailOk && usernameOk && passwordsOk
    }

    private fun checkEmail(email: String): Boolean {
        try {
            if (viewModel.verifyEmail(email)) {
                binding.emailTextInputLayout.error = ""

                return true
            }
            else {
                binding.emailTextInputLayout.error = resources.getString(R.string.invalid_email)
            }
        } catch (ex: IllegalArgumentException) {
            binding.emailTextInputLayout.error = resources.getString(R.string.field_with_illegal_characters)
        }

        return false
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

    private fun checkPasswords(password: String, repeatPassword: String): Boolean {
        try {
            if (viewModel.verifyMatchPasswords(password, repeatPassword)) {
                binding.passwordTextInputLayout.error = ""
                binding.repeatPwdTextInputLayout.error = ""

                return true
            }
            else {
                binding.passwordTextInputLayout.error = resources.getString(R.string.passwords_not_match)
                binding.repeatPwdTextInputLayout.error = resources.getString(R.string.passwords_not_match)
            }
        } catch (ex: IllegalArgumentException) {
            binding.passwordTextInputLayout.error = resources.getString(R.string.field_with_illegal_characters)
            binding.repeatPwdTextInputLayout.error = resources.getString(R.string.field_with_illegal_characters)
        }

        return false
    }

}