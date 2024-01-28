package com.eder.rider.main_activity

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.eder.rider.common.Progress
import com.eder.rider.common.toaster.Toaster
import com.eder.rider.logout.LogoutManager
import com.eder.rider.main_activity.databinding.ActivityMainBinding
import com.eder.rider.main_activity.di.MainViewModelFactory
import com.eder.rider.main_activity.viewmodels.MainViewModel
import com.eder.rider.preferences.sharedpreferences.UserAuthPrefs
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
    lateinit var logoutManager: LogoutManager

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
                    toaster.showLongToast(resources.getString(com.eder.rider.ui.R.string.something_went_wrong))

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.btnMenuLogout -> {
                logoutManager.logoutWithDialog(this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }

}