package com.eder.rider.requests.repositories

import com.eder.rider.common.model.UserAuth
import com.eder.rider.requests.model.UserLogin
import com.eder.rider.requests.model.UserRegister
import io.reactivex.disposables.Disposable

interface AuthRepository {

    fun login(
        userLogin: UserLogin,
        success: (UserAuth) -> Unit,
        failure: (String) -> Unit
    ): Disposable

    fun register(
        userRegister: UserRegister,
        success: () -> Unit,
        failure: (String) -> Unit
    ): Disposable

    fun test(): Disposable

}