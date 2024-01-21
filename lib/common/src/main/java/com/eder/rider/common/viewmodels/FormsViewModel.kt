package com.eder.rider.common.viewmodels

import androidx.lifecycle.ViewModel

abstract class FormsViewModel : ViewModel() {

    fun containsIllegalCharacters(text: String): Boolean {
        return illegalCharacters.any { text.contains(it) }
    }

    companion object {
        private val illegalCharacters = arrayListOf(";")
    }

}