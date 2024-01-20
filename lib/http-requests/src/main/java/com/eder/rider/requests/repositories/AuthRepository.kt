package com.eder.rider.requests.repositories

import es.evm.exmpl.common.model.UserAuth
import com.eder.rider.requests.model.UserLogin
import io.reactivex.disposables.Disposable

interface AuthRepository {

    fun login(
        userLogin: UserLogin,
        success: (UserAuth) -> Unit,
        failure: (String) -> Unit
    ): Disposable

    fun test(): Disposable

}