package com.eder.rider.requests.repositories

import com.eder.rider.requests.services.TestService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TestRepositoryDefault @Inject constructor(
    private val testService: TestService
) : TestRepository {

    override fun callTestEndpoint(
        success: (String) -> Unit,
        failure: (String) -> Unit
    ): Disposable {
        return testService.callTestEndpoint()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                success.invoke(it)
            }, {
                failure.invoke(it.message ?: "Failed")
            })
    }

}