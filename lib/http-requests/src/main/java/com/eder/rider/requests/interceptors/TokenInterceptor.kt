package com.eder.rider.requests.interceptors

import com.eder.rider.common.model.UserAuth
import com.eder.rider.preferences.sharedpreferences.UserAuthPrefs
import com.eder.rider.requests.RetrofitHelper
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response

class TokenInterceptor (
    private val gson: Gson,
    private val authPrefs: UserAuthPrefs,
    private val onForceLogout: () -> Unit
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()

        val authToken = authPrefs.getAccessToken()
        if (authToken.isNotBlank()) {
            requestBuilder.addHeader("Authorization", "Bearer $authToken")
        }
        else {
            return refreshAuthToken(chain, request)
        }

        val newRequest = requestBuilder.build()

        val response = chain.proceed(newRequest)

        return if (!response.isSuccessful && response.code == 401) {
            response.close()
            refreshAuthToken(chain, request)
        } else {
            response
        }
    }

    private fun refreshAuthToken(
        chain: Interceptor.Chain,
        originalRequest: Request
    ): Response {
        val newAuthToken = requestNewAuthToken(chain)

        return originalRequest.newBuilder()
            .addHeader("Authorization", "Bearer $newAuthToken")
            .build()
            .let {
                chain.proceed(it)
            }.also {
                if (!it.isSuccessful) // If still unsuccessful, log out
                    onForceLogout.invoke()
            }
    }

    private fun requestNewAuthToken(chain: Interceptor.Chain): String {
        val userAuth = authPrefs.getUserAuth()
        val userAuthJson = gson.toJson(userAuth)

        val request = Request.Builder()
            .url(RetrofitHelper.getAuthEndpoint())
            .post(
                userAuthJson.toRequestBody("application/json".toMediaTypeOrNull())
            )
            .addHeader("Accept", "application/json")
            .addHeader("Content-Type", "application/json")
            .build()

        val response = chain.proceed(request)

        if (response.isSuccessful) {
            val newUserAuth = gson.fromJson(response.body?.string(), UserAuth::class.java)
            newUserAuth?.let {
                authPrefs.saveUserAuth(newUserAuth)

                return newUserAuth.accessToken
            }

            return ""
        }
        else {
            return ""
        }
    }

}