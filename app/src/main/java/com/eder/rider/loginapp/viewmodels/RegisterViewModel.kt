package com.eder.rider.loginapp.viewmodels

import androidx.lifecycle.MutableLiveData
import com.eder.rider.requests.model.UserRegister
import com.eder.rider.requests.repositories.AuthRepository
import com.eder.rider.common.Progress
import com.eder.rider.common.logger.Logger
import com.eder.rider.common.viewmodels.FormsViewModel
import io.reactivex.disposables.Disposable

class RegisterViewModel(
    private val logger: Logger,
    private val authRepository: AuthRepository,
) : FormsViewModel() {

    val overlayProgressMld = MutableLiveData<Progress>().apply {
        value = Progress.hide()
    }

    private var registerDisposable: Disposable? = null
    fun register(
        userRegister: UserRegister,
        success: () -> Unit,
        failure: () -> Unit
    ) {
        overlayProgressMld.value = Progress.show()
        registerDisposable = authRepository.register(
            userRegister,
            {
                success.invoke()
            }, {
                logger.e(TAG, "Registration failed with msg: $it")
                failure.invoke()
            }
        )
    }

    @Throws(IllegalArgumentException::class)
    fun verifyEmail(email: String): Boolean {
        if (super.containsIllegalCharacters(email)) {
            throw IllegalArgumentException("Email contains illegal characters")
        }

        val regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$".toRegex()
        return email.matches(regex)
    }

    @Throws(IllegalArgumentException::class)
    fun verifyUsername(username: String): Boolean {
        if (super.containsIllegalCharacters(username)) {
            throw IllegalArgumentException("Username contains illegal characters")
        }

        return true
    }

    @Throws(IllegalArgumentException::class)
    fun verifyMatchPasswords(password: String, repeatPassword: String): Boolean {
        if (super.containsIllegalCharacters(password)) {
            throw IllegalArgumentException("Password contains illegal characters")
        }

        if (super.containsIllegalCharacters(repeatPassword)) {
            throw IllegalArgumentException("Repeat password field contains illegal characters")
        }

        return (password == repeatPassword)
    }

    override fun onCleared() {
        super.onCleared()
        registerDisposable?.dispose()
    }

    companion object {
        private const val TAG = "RegisterViewModel"
    }

}