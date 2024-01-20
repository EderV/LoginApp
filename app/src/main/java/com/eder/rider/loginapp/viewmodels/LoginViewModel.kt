package com.eder.rider.loginapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eder.rider.preferences.sharedpreferences.UserAuthPrefs
import com.eder.rider.requests.model.UserLogin
import com.eder.rider.requests.repositories.AuthRepository
import es.evm.exmpl.common.Progress
import es.evm.exmpl.common.logger.Logger
import io.reactivex.disposables.Disposable

class LoginViewModel(
    private val logger: Logger,
    private val authRepository: AuthRepository,
    private val userAuthPrefs: UserAuthPrefs,
) : ViewModel() {

    val overlayProgressMld = MutableLiveData<Progress>().apply {
        value = Progress.hide()
    }

    private var loginDisposable: Disposable? = null
    fun login(
        userLogin: UserLogin,
        success: () -> Unit,
        failure: () -> Unit
    ) {
        overlayProgressMld.value = Progress.show()
        authRepository.login(
            userLogin,
            {
                userAuthPrefs.saveUserAuth(it)
                success.invoke()
            }, {
                logger.e(TAG, "Login failed with msg: $it")
                failure.invoke()
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        loginDisposable?.dispose()
    }

    companion object {
        private const val TAG = "LoginViewModel"
    }

}