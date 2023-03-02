package com.example.suraapppractice.ki

import com.example.suraapppractice.flows.dashboard.viewmodels.SDDashboardViewModel
import com.example.suraapppractice.flows.login.viewmodels.SDLoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val sdViewModelsModule = module {
    viewModel { SDLoginViewModel(get(),get()) }
    viewModel { SDDashboardViewModel(get(),get()) }
}