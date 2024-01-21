package com.eder.rider.requests.repositories

import io.reactivex.disposables.Disposable

interface TestRepository {

    fun callTestEndpoint(
        success: (String) -> Unit,
        failure: (String) -> Unit
    ): Disposable

}