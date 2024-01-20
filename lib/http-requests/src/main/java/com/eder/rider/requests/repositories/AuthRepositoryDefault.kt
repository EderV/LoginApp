package com.eder.rider.requests.repositories

import android.util.Log
import es.evm.exmpl.common.model.UserAuth
import com.eder.rider.requests.model.UserLogin
import com.eder.rider.requests.services.AuthService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthRepositoryDefault @Inject constructor(
    private val authService: AuthService,
) : AuthRepository {

    override fun login(
        userLogin: UserLogin,
        success: (UserAuth) -> Unit,
        failure: (String) -> Unit
    ): Disposable {
        return authService.login(body = userLogin)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                success.invoke(it)
            }, {
                failure.invoke(it.message ?: "")
            })
    }

    override fun test(): Disposable {
        return authService.test()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.e("AAAAAAAAAAA", it ?: "nananana")
            }, {
                Log.e("AAAAAAAAAAA", it.stackTraceToString())
            })
    }

}