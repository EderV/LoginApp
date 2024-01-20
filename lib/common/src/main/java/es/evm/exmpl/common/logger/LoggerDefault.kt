package es.evm.exmpl.common.logger

import android.util.Log
import es.evm.exmpl.common.BuildConfig
import javax.inject.Inject

class LoggerDefault @Inject constructor() : Logger {

    override fun e(tag: String, message: String) {
        if (BuildConfig.DEBUG)
            Log.e(tag, message)
    }

    override fun d(tag: String, message: String) {
        if (BuildConfig.DEBUG)
            Log.d(tag, message)
    }

    override fun i(tag: String, message: String) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, message)
        }
    }

}