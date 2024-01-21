package com.eder.rider.loginapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eder.rider.loginapp.viewmodels.RegisterViewModel
import com.eder.rider.requests.repositories.AuthRepository
import com.eder.rider.common.logger.Logger
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class RegisterViewModelFactory @Inject constructor(
    private val logger: Logger,
    private val authRepository: AuthRepository,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            RegisterViewModel(logger, authRepository) as T
        }
        else {
            throw IllegalArgumentException("ViewModel not found: ${modelClass.name}")
        }
    }

}