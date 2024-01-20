package es.evm.exmpl.common

import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager

data class Resource<out T>(
    val status: Status,
    val message: String,
    val data: T?,
    val touchToRetry: Boolean = true,
    val noInternet: Boolean = false
) {

    companion object {
        fun <T> success(data: T) = Resource(Status.SUCCESS, "", data)

        fun <T> error(
            context: Context,
            message: String = "",
            touchToRetry: Boolean = true
        ) =
                Resource<T>(
                    Status.ERROR, message, null, touchToRetry,
                !NetworkUtils.isNetworkAvailable(context))

        fun <T> error(
            resources: Resources,
            connectivityManager: ConnectivityManager,
            message: String = "",
            touchToRetry: Boolean = true
        ) =
            Resource<T>(
                Status.ERROR, message, null, touchToRetry,
                !NetworkUtils.isNetworkAvailable(connectivityManager))

        fun <T> loading(message: String = "") = Resource<T>(Status.LOADING, message, null)
    }

    enum class Status {
        SUCCESS, LOADING, ERROR
    }

}
