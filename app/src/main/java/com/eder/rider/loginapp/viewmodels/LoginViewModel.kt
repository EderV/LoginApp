package com.eder.rider.loginapp.viewmodels

import androidx.lifecycle.MutableLiveData
import com.eder.rider.common.Progress
import com.eder.rider.common.logger.Logger
import com.eder.rider.common.viewmodels.FormsViewModel
import com.eder.rider.preferences.sharedpreferences.UserAuthPrefs
import com.eder.rider.requests.model.UserLogin
import com.eder.rider.requests.repositories.AuthRepository
import io.reactivex.disposables.Disposable

class LoginViewModel(
    private val logger: Logger,
    private val authRepository: AuthRepository,
    private val userAuthPrefs: UserAuthPrefs,
) : FormsViewModel() {

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
        loginDisposable = authRepository.login(
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

    @Throws(IllegalArgumentException::class)
    fun verifyUsername(username: String): Boolean {
        if (super.containsIllegalCharacters(username)) {
            throw IllegalArgumentException("Username contains illegal characters")
        }

        return true
    }

    @Throws(IllegalArgumentException::class)
    fun verifyPassword(password: String): Boolean {
        if (super.containsIllegalCharacters(password)) {
            throw IllegalArgumentException("Password contains illegal characters")
        }

        return true
    }

    override fun onCleared() {
        super.onCleared()
        loginDisposable?.dispose()
    }

    companion object {
        private const val TAG = "LoginViewModel"
    }

}