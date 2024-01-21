package com.eder.rider.main_activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.eder.rider.common.Progress
import com.eder.rider.common.toaster.Toaster
import com.eder.rider.main_activity.databinding.ActivityMainBinding
import com.eder.rider.main_activity.di.MainViewModelFactory
import com.eder.rider.main_activity.viewmodels.MainViewModel
import com.eder.rider.preferences.sharedpreferences.UserAuthPrefs
import com.eder.rider.ui.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var toaster: Toaster

    @Inject
    lateinit var userAuthPrefs: UserAuthPrefs

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Main"

        binding.btnTest.setOnClickListener {
            viewModel.testEndpoint(
                {
                    val resText = "${binding.tvResult.text} $it"
                    binding.tvResult.text = resText

                    viewModel.overlayProgressMld.value = Progress.hide()
                },
                {
                    toaster.showLongToast(resources.getString(R.string.something_went_wrong))

                    viewModel.overlayProgressMld.value = Progress.hide()
                }
            )

        }

        binding.btnLog.setOnClickListener {
            val user = userAuthPrefs.getUserAuth()

            Log.e(TAG, "User ID: ${user.userId}")
            Log.e(TAG, "User Access Token: ${user.accessToken}")
            Log.e(TAG, "User Refresh Token: ${user.refreshToken}")
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }

}