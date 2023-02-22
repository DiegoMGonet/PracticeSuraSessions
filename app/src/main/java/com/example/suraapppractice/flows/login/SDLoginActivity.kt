package com.example.suraapppractice.flows.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.suraapppractice.R
import com.example.suraapppractice.databinding.SdLoginActivityBinding
import com.example.suraapppractice.flows.dashboard.SDDashboardActivity
import com.example.suraapppractice.flows.login.actions.SDFieldType
import com.example.suraapppractice.flows.login.actions.SDLoginActions
import com.example.suraapppractice.flows.login.viewmodels.SDLoginViewModel
import com.example.suraapppractice.general.activities.SDBaseActivity
import com.example.suraapppractice.general.extensions.showMessage

class SDLoginActivity: SDBaseActivity() {
    private lateinit var binding: SdLoginActivityBinding
    private lateinit var errorFormat: String
    private val viewModel: SDLoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.sd_login_activity)
        errorFormat = getString(R.string.sura_demo_login_incorrect_format)

        viewModel.action.observe(this, Observer(this::observerActions))
        viewModel.loading.observe(this) { showLoading(it) }

        binding.apply {
            buttonEnter.setOnClickListener {
                showErrorUser(false)
                showErrorPassword(false)
                viewModel.login(
                    editTextUser.text.toString(),
                    editTextPassword.text.toString()
                )
            }
        }
    }

    private fun showErrorUser(show: Boolean) {
        binding.textLayoutUser.error = if (show) errorFormat else null
    }

    private fun showErrorPassword(show: Boolean) {
        binding.textLayoutPassword.error = if (show) errorFormat else null
    }

    private fun observerActions(action: SDLoginActions) {
        when(action) {
            is SDLoginActions.SDErrorDataValidation -> {
                when(action.field) {
                    SDFieldType.SD_USER_FIELD -> showErrorUser(true)
                    SDFieldType.SD_PASSWORD_FIELD -> showErrorPassword(true)
                }
            }
            is SDLoginActions.SDErrorService -> {
                showMessage(action.message)
            }
            SDLoginActions.SDLoginSuccess -> {
                println("--------------------- Login exitoso")
                val intent = Intent(this, SDDashboardActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}