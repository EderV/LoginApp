package com.eder.rider.loginapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eder.rider.requests.apis.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authService: AuthService
) : ViewModel() {

    val overlayProgressMld = MutableLiveData<Boolean>()
//    val authUser = MutableLiveData<UserAu>

}