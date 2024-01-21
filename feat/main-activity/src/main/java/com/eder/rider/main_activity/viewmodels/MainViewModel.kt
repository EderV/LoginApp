package com.eder.rider.main_activity.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eder.rider.common.Progress
import com.eder.rider.common.logger.Logger
import com.eder.rider.requests.repositories.TestRepository
import io.reactivex.disposables.Disposable

class MainViewModel(
    private val logger: Logger,
    private val testRepository: TestRepository,
) : ViewModel() {

    val overlayProgressMld = MutableLiveData<Progress>().apply {
        value = Progress.hide()
    }

    private var testDisposable: Disposable? = null
    fun testEndpoint(
        success: (String) -> Unit,
        failure: () -> Unit
    ) {
        overlayProgressMld.value = Progress.show()
        testDisposable = testRepository.callTestEndpoint(
            {
                success.invoke(it)
            }, {
                logger.e(TAG, "Call endpoint failed with msg: $it")
                failure.invoke()
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        testDisposable?.dispose()
    }

    companion object {
        private const val TAG = "MainViewModel"
    }

}