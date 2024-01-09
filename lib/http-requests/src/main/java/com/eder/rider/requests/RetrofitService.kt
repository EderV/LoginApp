package com.eder.rider.requests

interface RetrofitService {

    fun <T> createRetrofitService(clazz: Class<T>): T

}