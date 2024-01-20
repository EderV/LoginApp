package com.eder.rider.loginapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eder.rider.loginapp.viewmodels.LoginViewModel
import com.eder.rider.preferences.sharedpreferences.UserAuthPrefs
import com.eder.rider.requests.repositories.AuthRepository
import es.evm.exmpl.common.logger.Logger
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory @Inject constructor(
    private val logger: Logger,
    private val authRepository: AuthRepository,
    private val userAuthPrefs: UserAuthPrefs,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            LoginViewModel(logger, authRepository, userAuthPrefs) as T
        }
        else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}