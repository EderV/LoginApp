package com.eder.rider.common.toaster

import android.content.Context
import android.widget.Toast
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ToasterDefault @Inject constructor(
    @ApplicationContext private val context: Context
) : Toaster {

    override fun showShortToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLongToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}