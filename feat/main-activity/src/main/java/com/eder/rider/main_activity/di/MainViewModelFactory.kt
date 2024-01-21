package com.eder.rider.main_activity.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eder.rider.common.logger.Logger
import com.eder.rider.main_activity.viewmodels.MainViewModel
import com.eder.rider.requests.repositories.TestRepository
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory @Inject constructor(
    private val logger: Logger,
    private val testRepository: TestRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(logger, testRepository) as T
        }
        else {
            throw IllegalArgumentException("ViewModel not found: ${modelClass.name}")
        }
    }
}