package es.evm.exmpl.common

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtils {

    fun isNetworkAvailable(context: Context?) =
        (context?.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager)?.activeNetworkInfo?.isConnected ?: false

    fun getNetworkInfo(context: Context?): String =
        (context?.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager)?.activeNetworkInfo?.typeName ?: ""

    fun isNetworkAvailable(connectivityManager: ConnectivityManager?) =
        connectivityManager?.activeNetworkInfo?.isConnected ?: false

    fun getNetworkInfo(connectivityManager: ConnectivityManager?): String =
        connectivityManager?.activeNetworkInfo?.typeName ?: ""
}
